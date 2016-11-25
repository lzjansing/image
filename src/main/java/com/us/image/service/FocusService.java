package com.us.image.service;

import com.us.common.modules.sys.utils.UserUtil;
import com.us.common.persistence.Page;
import com.us.common.service.CrudService;
import com.us.common.utils.Message;
import com.us.image.dao.FocusDao;
import com.us.image.entities.Focus;
import com.us.image.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jansing on 16-11-6.
 */
@Service
@Transactional(readOnly = true)
public class FocusService extends CrudService<FocusDao, Focus> {
    @Autowired
    private ShareService shareService;

    @Transactional(readOnly = false)
    public Message focused(String userId) {
        Message message = null;
        //检查是否已关注
        Focus focus = new Focus();
        focus.setFromUser(UserUtil.getAccount().getUser());
        focus.setToUser(new User(userId));
        if (dao.get(focus) != null) {
            message = new Message(Message.FAIL, "已关注");
            return message;
        }

        //保存这条关注记录
        focus.setIsNew(Boolean.TRUE);
        super.save(focus);

        //取更新后的数据，返回
        message = new Message(Message.SUCCESS, "关注成功");
        message.getExtra().put("count", String.valueOf(countCurrentUser()));
        return message;
    }

    @Transactional(readOnly = false)
    public Message unfocused(String userId) {
        Message message = null;
        //检查是否已关注
        Focus focus = new Focus();
        focus.setFromUser(UserUtil.getAccount().getUser());
        focus.setToUser(new User(userId));
        if (dao.get(focus) == null) {
            message = new Message(Message.FAIL, "未关注");
            return message;
        }

        //删除这条关注记录
        dao.deleteSelf(focus);

        //取更新后的数据，返回
        message = new Message(Message.SUCCESS, "取关成功");
        message.getExtra().put("count", String.valueOf(countCurrentUser()));
        return message;
    }

    public int countCurrentUser() {
        return countCurrentUser(new Focus());
    }

    private int countCurrentUser(Focus focus) {
        focus.setFromUser(UserUtil.getAccount().getUser());
        return count(focus);
    }

    public int count(Focus focus) {
        return dao.count(focus);
    }

    @Override
    public Focus get(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Focus> findList(Focus entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<Focus> findPage(Page<Focus> page, Focus entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(Focus entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Focus entity) {
        throw new UnsupportedOperationException();
    }

}