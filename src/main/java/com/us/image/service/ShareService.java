package com.us.image.service;

import com.us.common.modules.sys.utils.UserUtil;
import com.us.common.persistence.Page;
import com.us.common.service.CrudService;
import com.us.common.utils.Message;
import com.us.image.dao.ShareDao;
import com.us.image.entities.Share;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by jansing on 16-11-6.
 */
@Service
@Transactional(readOnly = true)
public class ShareService extends CrudService<ShareDao, Share> {

    public Share get(String id) {
        return super.get(id);
    }

    public List<Share> findList(Share share) {
        return super.findList(share);
    }

    public Page<Share> findPage(Page<Share> page, Share share) {
        return super.findPage(page, share);
    }

    public int count(Share share) {
        return dao.count(share);
    }

    @Transactional(readOnly = false)
    public void save(Share share) {
        super.save(share);
    }

    @Transactional(readOnly = false)
    public Message updateSelf(Share share) {
        share.setCurrentUser(UserUtil.getAccount().getUser());
        share.setUpdateDate(LocalDateTime.now());
        Message message = new Message();
        if (dao.updateSelf(share) > 0) {
            message.setCode(Message.SUCCESS);
            message.setMessage("设置成功");
        } else {
            message.setCode(Message.FAIL);
            message.setMessage("设置失败");
        }
        return message;
    }

    //评论数、收藏数、点赞数自增1
    @Transactional(readOnly = false)
    public Message increase(Share share) {
        share.setCurrentUser(UserUtil.getAccount().getUser());
        share.setUpdateDate(LocalDateTime.now());
        Message message = new Message();
        if (dao.increase(share) > 0) {
            message.setCode(Message.SUCCESS);
            message.setMessage("设置成功");
        } else {
            message.setCode(Message.FAIL);
            message.setMessage("设置失败");
        }
        return message;
    }

    //评论数、收藏数、点赞数自减1
    @Transactional(readOnly = false)
    public Message decrease(Share share) {
        share.setCurrentUser(UserUtil.getAccount().getUser());
        share.setUpdateDate(LocalDateTime.now());
        Message message = new Message();
        if (dao.decrease(share) > 0) {
            message.setCode(Message.SUCCESS);
            message.setMessage("设置成功");
        } else {
            message.setCode(Message.FAIL);
            message.setMessage("设置失败");
        }
        return message;
    }

    @Transactional(readOnly = false)
    public void deleteSelf(String shareId) {
        Share share = new Share(shareId);
        share.setCurrentUser(UserUtil.getAccount().getUser());
        dao.deleteSelf(share);
    }

}