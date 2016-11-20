package com.us.image.service;

import com.us.common.service.BaseService;
import com.us.image.dao.AccountDao;
import com.us.image.entities.Account;
import com.us.image.exception.AccountAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2016/11/20 19:29
 * @description :
 */
@Service(value = "accountService")
public class AccountService extends BaseService {

    @Autowired
    @Qualifier(value = "accountDao")
    private AccountDao accountDao;

    // TODO log and java doc

    @Transactional
    public void insert(Account account) {
        String email = account.getEmail();
        if (accountDao.selectByEmail(email) != null) {
            throw new AccountAlreadyExistsException(String.format("邮箱[%s]已经被注册过了！", email));
        }
        accountDao.insert(account);
    }

    @Transactional
    public void delete(String id) {
        accountDao.delete(id);
    }

    @Transactional
    public void delete(Account account) {
        accountDao.delete(account.getId());
    }

    @Transactional(readOnly = true)
    public Account selectById(String id) {
        return accountDao.selectById(id);
    }

    @Transactional(readOnly = true)
    public Account selectByEmail(String email) {
        return accountDao.selectByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<Account> selectAll() {
        return accountDao.selectAll();
    }

}
