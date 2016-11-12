package com.us.image.service;

import com.us.common.persistence.Page;
import com.us.common.service.CrudService;
import com.us.image.dao.ShareDao;
import com.us.image.entities.Share;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = false)
    public void save(Share share) {
        super.save(share);
    }

    @Transactional(readOnly = false)
    public void delete(Share share) {
        super.delete(share);
    }

}