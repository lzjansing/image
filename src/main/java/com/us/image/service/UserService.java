package com.us.image.service;

import com.us.common.persistence.Page;
import com.us.common.service.CrudService;
import com.us.image.dao.UserDao;
import com.us.image.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jansing on 16-11-6.
 */
@Service
@Transactional(readOnly = true)
public class UserService extends CrudService<UserDao, User> {

    public User get(String id) {
        return super.get(id);
    }

    public List<User> findList(User user) {
        return super.findList(user);
    }

    public Page<User> findPage(Page<User> page, User user) {
        return super.findPage(page, user);
    }

    @Transactional(readOnly = false)
    public void save(User user) {
        super.save(user);
    }

    @Transactional(readOnly = false)
    public void delete(User user) {
        super.delete(user);
    }

    @Transactional(readOnly = false)
    public void disable(User user) {
        dao.disable(user);
    }

    @Transactional(readOnly = false)
    public void enable(User user) {
        dao.enable(user);
    }

}
