package com.us.common.modules.sys.security;

import com.us.common.config.Global;
import com.us.common.modules.sys.controller.LoginController;
import com.us.common.modules.sys.entities.Menu;
import com.us.common.modules.sys.entities.Role;
import com.us.common.modules.sys.entities.User;
import com.us.common.modules.sys.service.SystemService;
import com.us.common.modules.sys.utils.UserUtil;
import com.us.common.utils.Encodes;
import com.us.common.utils.StringUtil;
import com.us.image.service.AccountService;
import com.us.spring.utils.SpringContextHolder;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

/**
 * 从配置文件中取超管id
 * 如果用户被禁用，则可以登录，但没有用户权限
 * Created by jansing on 16-11-16.
 */
@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private CredentialsMatcher frontCredentialsMatcher;
    private SystemService systemService;
    @Autowired
    private AccountService accountService;

    public SystemAuthorizingRealm() {
    }

    public CredentialsMatcher getFrontCredentialsMatcher() {
        return frontCredentialsMatcher;
    }

    public void setFrontCredentialsMatcher(CredentialsMatcher frontCredentialsMatcher) {
        this.frontCredentialsMatcher = frontCredentialsMatcher;
    }

    /**
     * 构造登录信息，
     * 参考AuthenticatingRealm.getAuthenticationInfo()
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        int activeSessionSize = this.getSystemService().getSessionDao().getActiveSessions(false).size();
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("login submit, active session size: {}, username: {}", Integer.valueOf(activeSessionSize), token.getUsername());
        }

        if (LoginController.isValidateCodeLogin(token.getUsername(), false, false)) {
            Session session = UserUtil.getSession();
            String captcha = (String) session.getAttribute("CAPTCHA_SESSION_KEY");
            if (!captcha.equals(token.getCaptcha())) {
                throw new AuthenticationException("msg:验证码错误, 请重试.");
            }
        }

        if (token.isFrontEnd()) {
            com.us.image.entities.Account account = accountService.selectByEmail(token.getUsername());
            if (account != null) {
                return new SimpleAuthenticationInfo(
                        new Principal(account.getId(), account.getEmail(), token.isMobileLogin(), token.isFrontEnd()),
                        account.getPassword(),
                        Util.bytes(account.getEmail()),
                        this.getName());
            }
        } else {
            User user = this.getSystemService().getUserByUsername(token.getUsername());
            if (user != null) {
                byte[] salt = Encodes.decodeHex(user.getPassword().substring(0, 16));
                return new SimpleAuthenticationInfo(
                        new Principal(user.getId(), user.getUsername(), token.isMobileLogin(), token.isFrontEnd()),
                        user.getPassword().substring(16),
                        Util.bytes(salt),
                        this.getName());
            }
        }
        return null;
    }

    //权限
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Principal principal = (Principal) this.getAvailablePrincipal(principalCollection);
        if (!"true".equals(Global.getConfig("user.multiAccountLogin"))) {
            Collection<Session> sessionCollection = this.getSystemService().getSessionDao().getActiveSessions(true, principal, UserUtil.getSession());
            if (sessionCollection.size() > 0) {
                if (!UserUtil.getSubject().isAuthenticated()) {
                    UserUtil.getSubject().logout();
                    throw new AuthenticationException("msg:账号已在其它地方登录，请重新登录。");
                }

                Iterator<Session> sessionIterator = sessionCollection.iterator();

                while (sessionIterator.hasNext()) {
                    Session session = sessionIterator.next();
                    this.getSystemService().getSessionDao().delete(session);
                }
            }
        }
        if (principal.isFrontEnd()) {
            //前台用户无需任何权限
            return null;
        }

        User user = this.getSystemService().getUserByUsername(principal.getUsername());
        if (user == null || User.VALID_DISABLE.equals(user.getValid())) {
            return null;
        } else {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            Iterator<Menu> menuIterator = UserUtil.getMenuList().iterator();

            Menu menu;
            while (menuIterator.hasNext()) {
                do {
                    menu = menuIterator.next();
                } while (StringUtil.isBlank(menu.getPermission()) && menuIterator.hasNext());

                if (StringUtil.isNoneBlank(menu.getPermission())) {
                    String[] permissions = StringUtil.split(menu.getPermission(), ",");

                    for (int i = 0; i < permissions.length; ++i) {
                        simpleAuthorizationInfo.addStringPermission(permissions[i]);
                    }
                }
            }
            Iterator<Role> roleIterator = user.getRoleList().iterator();
            while (roleIterator.hasNext()) {
                simpleAuthorizationInfo.addRole(roleIterator.next().getEnname());
            }
            return simpleAuthorizationInfo;
        }
    }

    /**
     * 验证密码是否正确
     *
     * @param token
     * @param info
     * @throws AuthenticationException
     */
    @Override
    protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        CredentialsMatcher cm = usernamePasswordToken.isFrontEnd() ? this.getFrontCredentialsMatcher() : this.getCredentialsMatcher();
        if (cm != null) {
            if (!cm.doCredentialsMatch(token, info)) {
                String msg = "Submitted credentials for token [" + token + "] did not match the expected credentials.";
                throw new IncorrectCredentialsException(msg);
            }
        } else {
            throw new AuthenticationException("A CredentialsMatcher must be configured in order to verify credentials during authentication.  If you do not wish for credentials to be examined, you can configure an " + AllowAllCredentialsMatcher.class.getName() + " instance.");
        }
    }

    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("SHA-1");
        matcher.setHashIterations(1024);
        this.setCredentialsMatcher(matcher);

        HashedCredentialsMatcher frontMatcher = new HashedCredentialsMatcher("MD5");
        frontMatcher.setHashIterations(1);
        this.setFrontCredentialsMatcher(frontMatcher);
    }

    public SystemService getSystemService() {
        if (this.systemService == null) {
            this.systemService = SpringContextHolder.getBean(SystemService.class);
        }

        return this.systemService;
    }

    public static class Principal implements Serializable {
        private String id;
        private String username;
        private boolean mobileLogin;
        private boolean frontEnd;

        public Principal(String userId, String username, boolean mobileLogin, boolean frontEnd) {
            this.id = userId;
            this.username = username;
            this.mobileLogin = mobileLogin;
            this.frontEnd = frontEnd;
        }

        public String getId() {
            return this.id;
        }

        public String getUsername() {
            return this.username;
        }

        public boolean isMobileLogin() {
            return this.mobileLogin;
        }

        public boolean isFrontEnd() {
            return this.frontEnd;
        }

        public String getSessionid() {
            try {
                return (String) UserUtil.getSession().getId();
            } catch (Exception var2) {
                return "";
            }
        }

        public String toString() {
            return this.id;
        }
    }
}
