package com.us.image.service;

import com.us.common.persistence.Page;
import com.us.common.service.CrudService;
import com.us.image.dao.FocusDao;
import com.us.image.entities.Focus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jansing on 16-11-6.
 */
@Service
@Transactional(readOnly = true)
public class FocusService extends CrudService<FocusDao, Focus> {

    public Focus get(String id) {
        return super.get(id);
    }

    public List<Focus> findList(Focus focus) {
        return super.findList(focus);
    }

    public Page<Focus> findPage(Page<Focus> page, Focus focus) {
        return super.findPage(page, focus);
    }

    @Transactional(readOnly = false)
    public void save(Focus focus) {
        super.save(focus);
    }

    @Transactional(readOnly = false)
    public void delete(Focus focus) {
        super.delete(focus);
    }

}