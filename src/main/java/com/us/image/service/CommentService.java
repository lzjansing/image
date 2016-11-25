package com.us.image.service;

import com.us.common.modules.sys.utils.UserUtil;
import com.us.common.persistence.Page;
import com.us.common.service.CrudService;
import com.us.common.utils.Message;
import com.us.common.utils.StringUtil;
import com.us.image.dao.CommentDao;
import com.us.image.entities.Comment;
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
public class CommentService extends CrudService<CommentDao, Comment> {
    @Autowired
    private ShareService shareService;

    public Comment get(String id) {
        throw new UnsupportedOperationException();
    }

    public List<Comment> findList(Comment comment) {
        return super.findList(comment);
    }

    public Page<Comment> findPage(Page<Comment> page, Comment comment) {
        return super.findPage(page, comment);
    }

    @Transactional(readOnly = false)
    public Message comment(Comment comment) {
        Message message = null;

        //更新share评论数
        comment.getShare().setComment(Integer.valueOf(1));
        message = shareService.increase(comment.getShare());

        //保存这条评论
        Share share = shareService.get(comment.getShare().getId());
        comment.setFromUser(UserUtil.getAccount().getUser());
        comment.setIsNew(Boolean.TRUE);
        //根据回复分享/评论 设置
        if(comment.getParent()==null || StringUtil.isBlank(comment.getParent().getId())) {
            comment.setToUser(share.getUser());
            comment.setParent(new Comment());
        }else{
            Comment parent = dao.get(comment.getParent().getId());
            comment.setToUser(parent.getFromUser());
            comment.setPids(parent.getPids()+","+parent.getId());
        }
        super.save(comment);

        //更新后的数据
        message.getExtra().put("count", share.getComment());
        message.getExtra().put("item", comment);
        return message;
    }

    @Transactional(readOnly = false)
    public Message uncomment(String commentId){
        Message message = null;
        //检查是否已评论
        Comment comment = dao.get(commentId);
        if(comment==null){
            message = new Message(Message.FAIL, "未评论");
            return message;
        }

        //更新share评论数
        comment.getShare().setComment(Integer.valueOf(1));
        message = shareService.decrease(comment.getShare());

        //删除这条评论记录
        comment.setFromUser(UserUtil.getAccount().getUser());
        dao.deleteSelf(comment);

        //取更新后的数据，返回
        Share share = shareService.get(comment.getShare().getId());
        message.getExtra().put("count", String.valueOf(share.getComment()));
        return message;
    }

    public List<Comment> findParentList(String pids){
        return dao.findParentList(pids.split(","));
    }

    @Transactional(readOnly = false)
    public void delete(Comment comment) {
        throw new UnsupportedOperationException();
    }

}