package com.us.image.utils;

import com.us.image.dao.UserDao;
import com.us.image.entities.User;
import com.us.spring.utils.SpringContextHolder;

import javax.servlet.http.HttpSession;

/**
 * Created by jansing on 16-11-9.
 */
public class UserUtil {
    private static UserDao userDao = (UserDao) SpringContextHolder.getBean(UserDao.class);


    public static User get(String id) {
        return userDao.get(id);
    }

    public static User getByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return userDao.getByUsername(user);
    }

    public static User getUser(HttpSession session) {
        User user = (User) session.getAttribute("loginUser");
        return user != null ? user : new User();
    }

}
