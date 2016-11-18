package com.us.common.modules.sys.dao;

import com.us.common.modules.sys.entities.User;
import com.us.common.persistence.CrudDao;
import com.us.common.persistence.annotation.MyBatisDao;

/**
 * Created by jansing on 16-11-6.
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {

    public User getByUsername(User user);

    public void disable(User user);

    public void enable(User user);

    long findAllCount(User var1);

    int updatePasswordById(User var1);

    int deleteUserRole(User var1);

    int insertUserRole(User var1);
}
