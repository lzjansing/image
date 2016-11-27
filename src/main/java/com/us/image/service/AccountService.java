package com.us.image.service;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;
import com.us.common.persistence.Page;
import com.us.common.service.BaseService;
import com.us.common.utils.Collections3;
import com.us.common.utils.StringUtil;
import com.us.image.dao.AccountDao;
import com.us.image.entities.Account;
import com.us.image.entities.User;
import com.us.image.exception.AccountAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    /**
     * @param page
     * @param account
     * @return Page<User>
     */
    @Transactional(readOnly = true)
    public Page findFocusPage(Page page, Account account) {
        account.setPage(page);
        page.setList(accountDao.findFocusList(account));
        return page;
    }

    /**
     * @param page
     * @param account
     * @return Page<User>
     */
    @Transactional(readOnly = true)
    public Page findBeFocusedPage(Page page, Account account) {
        account.setPage(page);
        page.setList(accountDao.findBeFocusedList(account));

        return page;
    }

    /**
     * 找出userList中已被currentUser关注的user，并将其user.setHadFocused(false)
     *
     * @param userList
     * @param currentUser
     */
    public void beFocused(List<User> userList, User currentUser) {
        if (currentUser == null || StringUtil.isBlank(currentUser.getId()) || userList.size() == 0) {
            return;
        }
        List<String> ids = Collections3.extractToList(userList, "id");
        Map<String, Object> map = Maps.newHashMap();
        map.put("ids", ids);
        map.put("userId", currentUser.getId());
        List<String> hadFocusedIds = accountDao.findFocusListByMap(map);
        if (hadFocusedIds.size() <= 0) {
            return;
        }
        userList.stream().filter(user -> hadFocusedIds.contains(user.getId())).forEach(user -> user.setHadFocused(true));
        return;
    }

    public void beFocused(User targetUser, User currentUser) {
        List<User> userList = Lists.newArrayList();
        userList.add(targetUser);
        beFocused(userList, currentUser);
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
