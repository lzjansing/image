package com.us.common.modules.sys.utils;

import com.us.common.config.Global;
import com.us.common.modules.sys.dao.MenuDao;
import com.us.common.modules.sys.dao.RoleDao;
import com.us.common.modules.sys.dao.UserDao;
import com.us.common.modules.sys.entities.Menu;
import com.us.common.modules.sys.entities.Role;
import com.us.common.modules.sys.entities.User;
import com.us.common.modules.sys.security.SystemAuthorizingRealm.Principal;
import com.us.image.dao.AccountDao;
import com.us.image.entities.Account;
import com.us.spring.utils.CacheUtil;
import com.us.spring.utils.SpringContextHolder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;

/**
 * Created by jansing on 16-11-9.
 */
public class UserUtil {
    private static UserDao userDao = (UserDao) SpringContextHolder.getBean(UserDao.class);
    private static RoleDao roleDao = (RoleDao) SpringContextHolder.getBean(RoleDao.class);
    private static MenuDao menuDao = (MenuDao) SpringContextHolder.getBean(MenuDao.class);
    private static AccountDao accountDao = (AccountDao) SpringContextHolder.getBean(AccountDao.class);
    public static final String USER_CACHE = "userCache";
    public static final String USER_CACHE_ID_ = "id_";
    public static final String USER_CACHE_USERNAME_ = "un_";
    public static final String CACHE_ROLE_LIST = "roleList";
    public static final String CACHE_MENU_LIST = "menuList";

    public static User get(String id) {
        User user = (User) CacheUtil.get(USER_CACHE, USER_CACHE_ID_ + id);
        if (user != null) {
            return user;
        }
        user = userDao.get(id);
        if (user != null) {
            user.setRoleList(roleDao.findList(new Role(user)));
            CacheUtil.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            CacheUtil.put(USER_CACHE, USER_CACHE_USERNAME_ + user.getUsername(), user);
        }
        return user;
    }

    public static User getByUsername(String username) {
        User user = (User) CacheUtil.get(USER_CACHE, USER_CACHE_USERNAME_ + username);
        if (user != null) {
            return user;
        }
        user = userDao.getByUsername(new User(null, username));
        if (user != null) {
            user.setRoleList(roleDao.findList(new Role(user)));
            CacheUtil.put(USER_CACHE, USER_CACHE_ID_ + user.getId(), user);
            CacheUtil.put(USER_CACHE, USER_CACHE_USERNAME_ + user.getUsername(), user);
        }
        return user;
    }

    public static User getUser() {
        Principal principal = getPrincipal();
        if (principal != null) {
            if (principal.isFrontEnd()) {
                return null;
            }
            User user = get(principal.getId());
            return user != null ? user : new User();
        } else {
            return new User();
        }
    }

    public static Account getAccount() {
        Principal principal = getPrincipal();

        if (principal != null) {
            if (!principal.isFrontEnd()) {
                return null;
            }
            Account account = accountDao.selectById(principal.getId());
            return account != null ? account : new Account();
        } else {
            return new Account();
        }
    }

    public static void clearCache() {
        removeCache(CACHE_ROLE_LIST);
        removeCache(CACHE_MENU_LIST);
        clearCache(getUser());
    }

    public static void clearCache(User user) {
        CacheUtil.remove(USER_CACHE, USER_CACHE_ID_ + user.getId());
        CacheUtil.remove(USER_CACHE, USER_CACHE_USERNAME_ + user.getUsername());
    }

    //根据user取其roleList
    public static List<Role> getRoleList() {
        List roleList = (List) getCache(CACHE_ROLE_LIST);
        if (roleList == null) {
            User user = getUser();
            if (user == null) {
                return null;
            }
            if (isAdmin(user)) {
                roleList = roleDao.findList(new Role());
            } else {
                Role role = new Role(user);
                roleList = roleDao.findList(role);
            }
            putCache(CACHE_ROLE_LIST, roleList);
        }

        return roleList;
    }

    //根据user取其菜单
    public static List<Menu> getMenuList() {
        List menuList = (List) getCache(CACHE_MENU_LIST);
        if (menuList == null) {
            User user = getUser();
            if (user == null) {
                return null;
            }
            Menu m = new Menu();
            if (isAdmin(user)) {
                menuList = menuDao.findByUserId(m);
            } else {
                m.setUser(user);
                menuList = menuDao.findByUserId(m);
            }
            putCache(CACHE_MENU_LIST, menuList);
        }

        return menuList;
    }

    public static boolean isAdmin() {
        return isAdmin(getUser().getId());
    }

    public static boolean isAdmin(User user) {
        return isAdmin(user.getId());
    }

    public static boolean isAdmin(String id) {
        return id != null && id.equals(Global.getSuperAdminId());
    }

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }


    public static Principal getPrincipal() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return principal;
            }
        } catch (UnavailableSecurityManagerException var2) {
        } catch (InvalidSessionException var3) {
        }

        return null;
    }

    public static Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }

            if (session != null) {
                return session;
            }
        } catch (InvalidSessionException var2) {
        }

        return null;
    }

    public static Object getCache(String key) {
        return getCache(key, null);
    }

    public static Object getCache(String key, Object defaultValue) {
        Object obj = getSession().getAttribute(key);
        return obj == null ? defaultValue : obj;
    }

    public static void putCache(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static void removeCache(String key) {
        getSession().removeAttribute(key);
    }
}
