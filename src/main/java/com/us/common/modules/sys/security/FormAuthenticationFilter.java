package com.us.common.modules.sys.security;

import com.us.common.utils.StringUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by jansing on 16-11-17.
 */
@Service
public class FormAuthenticationFilter extends org.apache.shiro.web.filter.authc.FormAuthenticationFilter {
    public static final String DEFAULT_CAPTCHA_PARAM = "validateCode";
    public static final String DEFAULT_MOBILE_PARAM = "mobileLogin";
    public static final String DEFAULT_MESSAGE_PARAM = "message";
    public static final String DEFAULT_FRONT_LOGIN_URL = "/login.jsp";
    public static final String DEFAULT_FRONT_SUCCESS_URL = "/";
    private String captchaParam = DEFAULT_CAPTCHA_PARAM;
    private String mobileLoginParam = DEFAULT_MOBILE_PARAM;
    private String messageParam = DEFAULT_MESSAGE_PARAM;
    private String frontLoginUrl = DEFAULT_FRONT_LOGIN_URL;
    private String frontSuccessUrl = DEFAULT_FRONT_SUCCESS_URL;

    public FormAuthenticationFilter() {
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = this.getUsername(request);
        char[] password = this.getPassword(request).toCharArray();
        if (password == null) {
            password = new char[0];
        }

        boolean rememberMe = this.isRememberMe(request);
        String host = StringUtil.getRemoteAddr((HttpServletRequest) request);
        String captcha = this.getCaptcha(request);
        boolean mobile = this.isMobileLogin(request);
        return new UsernamePasswordToken(username, password, rememberMe, host, captcha, mobile);
    }

    public String getCaptchaParam() {
        return this.captchaParam;
    }

    protected String getCaptcha(ServletRequest request) {
        return WebUtils.getCleanParam(request, this.getCaptchaParam());
    }

    public String getMobileLoginParam() {
        return this.mobileLoginParam;
    }

    protected boolean isMobileLogin(ServletRequest request) {
        return WebUtils.isTrue(request, this.getMobileLoginParam());
    }

    public String getMessageParam() {
        return this.messageParam;
    }

    public String getFrontLoginUrl() {
        return frontLoginUrl;
    }

    public void setFrontLoginUrl(String frontLoginUrl) {
        this.frontLoginUrl = frontLoginUrl;
    }

    public String getFrontSuccessUrl() {
        return frontSuccessUrl;
    }

    public void setFrontSuccessUrl(String frontSuccessUrl) {
        this.frontSuccessUrl = frontSuccessUrl;
    }

    @Override
    public boolean isLoginRequest(ServletRequest request, ServletResponse response) {
        return this.pathsMatch(this.getFrontLoginUrl(), request)||this.pathsMatch(this.getLoginUrl(), request);
    }

    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        if(this.pathsMatch(getFrontLoginUrl(),request)) {
            WebUtils.issueRedirect(request, response, this.getFrontLoginUrl(), null, true);
        }else if(this.pathsMatch(getLoginUrl(),request)) {
            WebUtils.issueRedirect(request, response, this.getLoginUrl(), null, true);
        }
    }

    //登录失败时，执行该方法后，访问匹配的controller方法
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String className = e.getClass().getName();
        String message = "";
        if (!IncorrectCredentialsException.class.getName().equals(className) && !UnknownAccountException.class.getName().equals(className)) {
            if (e.getMessage() != null && StringUtil.startsWith(e.getMessage(), "msg:")) {
                message = StringUtil.replace(e.getMessage(), "msg:", "");
            } else {
                message = "系统出现点问题，请稍后再试！";
                e.printStackTrace();
            }
        } else {
            message = "用户或密码错误, 请重试.";
        }

        request.setAttribute(this.getFailureKeyAttribute(), className);
        request.setAttribute(this.getMessageParam(), message);
        return true;
    }

    @Override
    protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
        //前台后台登录成功后分别跳转的链接
        if(this.pathsMatch(getFrontLoginUrl(),request)) {
            WebUtils.issueRedirect(request, response, this.getFrontSuccessUrl(), null, true);
        }else if(this.pathsMatch(getLoginUrl(),request)) {
            WebUtils.issueRedirect(request, response, this.getSuccessUrl(), null, true);
        }
    }

}

