package com.us.image.service;

import com.us.common.modules.sys.utils.UserUtil;
import com.us.common.persistence.Page;
import com.us.common.service.CrudService;
import com.us.common.utils.Message;
import com.us.image.dao.PraiseDao;
import com.us.image.entities.Praise;
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
public class PraiseService extends CrudService<PraiseDao, Praise> {
    @Autowired
    private ShareService shareService;

    @Transactional(readOnly = false)
    public Message praised(String shareId){
        Message message = null;
        //检查是否已赞
        Praise praise = new Praise();
        praise.setFromUser(UserUtil.getAccount().getUser());
        praise.setShare(new Share(shareId));
        if(dao.get(praise)!=null){
            message = new Message(Message.FAIL, "已赞");
            return message;
        }

        //更新share赞数
        Share share = new Share(shareId);
        share.setPraise(Integer.valueOf(1));
        message = shareService.increase(share);

        //保存这条赞记录
        share = shareService.get(shareId);
        praise.setToUser(share.getUser());
        praise.setIsNew(Boolean.TRUE);
        super.save(praise);

        //取更新后的数据，返回
        message.getExtra().put("count", String.valueOf(share.getPraise()));
        return message;
    }

    @Transactional(readOnly = false)
    public Message unpraised(String shareId){
        Message message = null;
        //检查是否已赞
        Praise praise = new Praise();
        praise.setFromUser(UserUtil.getAccount().getUser());
        praise.setShare(new Share(shareId));
        if(dao.get(praise)==null){
            message = new Message(Message.FAIL, "未赞");
            return message;
        }

        //更新share赞数
        Share share = new Share(shareId);
        share.setPraise(Integer.valueOf(1));
        message = shareService.decrease(share);

        //删除这条赞记录
        dao.deleteSelf(praise);

        //取更新后的数据，返回
        share = shareService.get(shareId);
        message.getExtra().put("count", String.valueOf(share.getPraise()));
        return message;
    }

    public int countCurrentUser(){
        return countCurrentUser(new Praise());
    }

    public int countNewCurrentUser(){
        Praise praise = new Praise();
        praise.setIsNew(Boolean.TRUE);
        return countCurrentUser(praise);
    }

    private int countCurrentUser(Praise praise){
        praise.setFromUser(UserUtil.getAccount().getUser());
        return count(praise);
    }

    private int count(Praise praise){
        return dao.count(praise);
    }

    @Override
    public Praise get(String id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Praise> findList(Praise entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<Praise> findPage(Page<Praise> page, Praise entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void save(Praise entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Praise entity) {
        throw new UnsupportedOperationException();
    }

}