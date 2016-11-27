package com.us.image.dao;

import com.us.common.persistence.annotation.MyBatisDao;
import com.us.image.entities.Account;
import com.us.image.entities.User;

import java.util.List;
import java.util.Map;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2016/11/20 19:11
 * @description :
 */
@MyBatisDao
public interface AccountDao {

    void insert(Account account);

    void delete(String id);

    //void update(Account account);

    int updateLocked(Account account);

    Account selectById(String id);

    Account selectByEmail(String email);

    List<Account> selectAll();

    List<Account> findList(Account account);

    List<User> findFocusList(Account account);

    List<User> findBeFocusedList(Account account);

    List<String> findFocusListByMap(Map<String, Object> map);

}
