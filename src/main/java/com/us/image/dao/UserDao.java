package com.us.image.dao;

import com.us.common.persistence.CrudDao;
import com.us.common.persistence.annotation.MyBatisDao;
import com.us.image.entities.User;

/**
 * Created by jansing on 16-11-6.
 */
@MyBatisDao
public interface UserDao extends CrudDao<User> {

    public User getByUsername(User user);

    public void disable(User user);

    public void enable(User user);
}
