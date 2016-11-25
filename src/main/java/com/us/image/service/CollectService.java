package com.us.image.service;

import com.us.common.modules.sys.utils.UserUtil;
import com.us.common.persistence.Page;
import com.us.common.service.CrudService;
import com.us.common.utils.Message;
import com.us.image.dao.CollectDao;
import com.us.image.entities.Collect;
import com.us.image.entities.Share;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jansing on 16-11-6.
 */
@Service
@Transactional(readOnly = true)
public class CollectService extends CrudService<CollectDao, Collect> {
    @Autowired
    private ShareService shareService;

    @Transactional(readOnly = false)
    public Message collected(String shareId) {
        Message message = null;
        //检查是否已收藏
        Collect collect = new Collect();
        collect.setFromUser(UserUtil.getAccount().getUser());
        collect.setShare(new Share(shareId));
        if (dao.get(collect) != null) {
            message = new Message(Message.FAIL, "已收藏");
            return message;
        }

        //更新share收藏数
        Share share = new Share(shareId);
        share.setCollect(Integer.valueOf(1));
        message = shareService.increase(share);

        //保存这条收藏记录
        share = shareService.get(shareId);
        collect.setToUser(share.getUser());
        collect.setIsNew(Boolean.TRUE);
        super.save(collect);

        //取更新后的数据，返回
        message.getExtra().put("count", String.valueOf(share.getCollect()));
        return message;
    }

    @Transactional(readOnly = false)
    public Message uncollected(String shareId) {
        Message message = null;
        //检查是否已收藏
        Collect collect = new Collect();
        collect.setFromUser(UserUtil.getAccount().getUser());
        collect.setShare(new Share(shareId));
        if (dao.get(collect) == null) {
            message = new Message(Message.FAIL, "未收藏");
            return message;
        }

        //更新share收藏数
        Share share = new Share(shareId);
        share.setCollect(Integer.valueOf(1));
        message = shareService.decrease(share);

        //删除这条收藏记录
        dao.deleteSelf(collect);

        //取更新后的数据，返回
        share = shareService.get(shareId);
        message.getExtra().put("count", String.valueOf(share.getCollect()));
        return message;
    }

    public int countCurrentUser() {
        return countCurrentUser(new Collect());
    }

    public int countNewCurrentUser() {
        Collect collect = new Collect();
        collect.setIsNew(Boolean.TRUE);
        return countCurrentUser(collect);
    }

    private int countCurrentUser(Collect collect) {
        collect.setFromUser(UserUtil.getAccount().getUser());
        return count(collect);
    }

    private int count(Collect collect) {
        return dao.count(collect);
    }

    @Override
    public Collect get(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Collect> findList(Collect entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<Collect> findPage(Page<Collect> page, Collect entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(Collect entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Collect entity) {
        throw new UnsupportedOperationException();
    }

}