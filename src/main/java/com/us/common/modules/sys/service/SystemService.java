package com.us.common.modules.sys.service;

/**
 * Created by jansing on 16-11-16.
 */

import com.us.common.config.Global;
import com.us.common.modules.sys.dao.MenuDao;
import com.us.common.modules.sys.dao.RoleDao;
import com.us.common.modules.sys.dao.UserDao;
import com.us.common.modules.sys.entities.Menu;
import com.us.common.modules.sys.entities.Role;
import com.us.common.modules.sys.entities.User;
import com.us.common.modules.sys.security.SessionDAO;
import com.us.common.modules.sys.security.SystemAuthorizingRealm;
import com.us.common.modules.sys.utils.UserUtil;
import com.us.common.persistence.Page;
import com.us.common.security.Digests;
import com.us.common.service.BaseService;
import com.us.common.service.ServiceException;
import com.us.common.utils.Encodes;
import com.us.common.utils.StringUtil;
import com.us.spring.utils.CacheUtil;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SystemService extends BaseService {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private SessionDAO sessionDao;
    @Autowired
    private SystemAuthorizingRealm systemRealm;

    public SystemService() {
    }

    public SessionDAO getSessionDao() {
        return this.sessionDao;
    }

    public User getUser(String id) {
        return UserUtil.get(id);
    }

    public User getUserByUsername(String username) {
        return UserUtil.getByUsername(username);
    }

    public Page<User> findUser(Page<User> page, User user) {
        user.setPage(page);
        page.setList(this.userDao.findList(user));
        return page;
    }

    public List<User> findUser(User user) {
        List<User> list = this.userDao.findList(user);
        return list;
    }

    @Transactional(readOnly = false)
    public void saveUser(User user) {
        if (StringUtil.isBlank(user.getId())) {
            user.preInsert();
            this.userDao.insert(user);
        } else {
            user.preUpdate();
            this.userDao.update(user);
        }

        if (StringUtil.isNotBlank(user.getId())) {
            this.userDao.deleteUserRole(user);
            if (user.getRoleList() == null || user.getRoleList().size() <= 0) {
                throw new ServiceException(user.getUsername() + "没有设置角色！");
            }

            this.userDao.insertUserRole(user);
            UserUtil.clearCache(user);
        }
    }

    @Transactional(readOnly = false)
    public void deleteUser(User user) {
        this.userDao.delete(user);
        UserUtil.clearCache(user);
    }

    @Transactional(readOnly = false)
    public void disableUser(User user) {
        this.userDao.disable(user);
        UserUtil.clearCache(user);
    }

    @Transactional(readOnly = false)
    public void enableUser(User user) {
        this.userDao.enable(user);
        UserUtil.clearCache(user);
    }

    @Transactional(readOnly = false)
    public void updatePasswordById(String id, String username, String newPassword) {
        User user = new User(id);
        user.setPassword(encryptPassword(newPassword));
        this.userDao.updatePasswordById(user);
        user.setUsername(username);
        UserUtil.clearCache(user);
    }

    public static String encryptPassword(String plainPassword) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    public static boolean validatePassword(String plainPassword, String password) {
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }

    public Collection<Session> getActiveSessions() {
        return this.sessionDao.getActiveSessions(false);
    }

    public Role getRole(String id) {
        return this.roleDao.get(id);
    }

    public Role getRoleByName(String name) {
        Role r = new Role();
        r.setName(name);
        return this.roleDao.getByName(r);
    }

    public Role getRoleByEnname(String enname) {
        Role r = new Role();
        r.setEnname(enname);
        return this.roleDao.getByEnname(r);
    }

    public List<Role> findRole(Role role) {
        return this.roleDao.findList(role);
    }

    public List<Role> findAllRole() {
        return UserUtil.getRoleList();
    }

    @Transactional(readOnly = false)
    public void saveRole(Role role) {
        if (StringUtil.isBlank(role.getId())) {
            role.preInsert();
            this.roleDao.insert(role);
        } else {
            role.preUpdate();
            this.roleDao.update(role);
        }

        this.roleDao.deleteRoleMenu(role);
        if (role.getMenuList().size() > 0) {
            this.roleDao.insertRoleMenu(role);
        }

        UserUtil.removeCache(UserUtil.CACHE_ROLE_LIST);
    }

    @Transactional(readOnly = false)
    public void deleteRole(Role role) {
        this.roleDao.delete(role);
        UserUtil.removeCache(UserUtil.CACHE_ROLE_LIST);
    }

    @Transactional(readOnly = false)
    public Boolean outUserInRole(Role role, User user) {
        List roles = user.getRoleList();
        Iterator var4 = roles.iterator();

        Role e;
        do {
            if (!var4.hasNext()) {
                return Boolean.valueOf(false);
            }

            e = (Role) var4.next();
        } while (!e.getId().equals(role.getId()));

        roles.remove(e);
        this.saveUser(user);
        return Boolean.valueOf(true);
    }

    @Transactional(readOnly = false)
    public User assignUserToRole(Role role, User user) {
        if (user == null) {
            return null;
        } else {
            if (user.hasRole(role)) {
                return null;
            } else {
                user.getRoleList().add(role);
                this.saveUser(user);
                return user;
            }
        }
    }

    public Menu getMenu(String id) {
        return this.menuDao.get(id);
    }

    public List<Menu> findAllMenu() {
        return UserUtil.getMenuList();
    }

    @Transactional(readOnly = false)
    public void saveMenu(Menu menu) {
        menu.setParent(this.getMenu(menu.getParent().getId()));
        String oldParentIds = menu.getPids();
        menu.setPids(menu.getParent().getPids() + menu.getParent().getId() + ",");
        if (StringUtil.isBlank(menu.getId())) {
            menu.preInsert();
            this.menuDao.insert(menu);
        } else {
            menu.preUpdate();
            this.menuDao.update(menu);
        }

        Menu m = new Menu();
        m.setPids(menu.getId());
        List<Menu> list = this.menuDao.findByParentIdsLike(m);
        Iterator<Menu> iterator = list.iterator();

        while (iterator.hasNext()) {
            m = iterator.next();
            m.setPids(m.getPids().replace(oldParentIds, menu.getPids()));
            this.menuDao.updateParentIds(m);
        }

        UserUtil.removeCache(UserUtil.CACHE_MENU_LIST);
        CacheUtil.remove(CacheUtil.CACHE_MENU_NAME_PATH_MAP);
    }

    @Transactional(readOnly = false)
    public void updateMenuSort(Menu menu) {
        this.menuDao.updateSort(menu);
        UserUtil.removeCache(UserUtil.CACHE_MENU_LIST);
        CacheUtil.remove(CacheUtil.CACHE_MENU_NAME_PATH_MAP);
    }

    @Transactional(readOnly = false)
    public void deleteMenu(Menu menu) {
        this.menuDao.delete(menu);
        UserUtil.removeCache(UserUtil.CACHE_MENU_LIST);
        CacheUtil.remove(CacheUtil.CACHE_MENU_NAME_PATH_MAP);
    }

    public static boolean printKeyLoadMessage() {
        StringBuilder sb = new StringBuilder();
        sb.append("\r\n======================================================================\r\n");
        sb.append("\r\n    欢迎使用 " + Global.getConfig("productName") + "  - Powered By jansing \r\n");
        sb.append("\r\n======================================================================\r\n");
        System.out.println(sb.toString());
        return true;
    }
}
