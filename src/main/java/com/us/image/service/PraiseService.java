package com.us.image.service;

import com.us.common.persistence.Page;
import com.us.common.service.CrudService;
import com.us.image.dao.PraiseDao;
import com.us.image.entities.Praise;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jansing on 16-11-6.
 */
@Service
@Transactional(readOnly = true)
public class PraiseService extends CrudService<PraiseDao, Praise> {

    public Praise get(String id) {
        return super.get(id);
    }

    public List<Praise> findList(Praise praise) {
        return super.findList(praise);
    }

    public Page<Praise> findPage(Page<Praise> page, Praise praise) {
        return super.findPage(page, praise);
    }

    @Transactional(readOnly = false)
    public void save(Praise praise) {
        super.save(praise);
    }

    @Transactional(readOnly = false)
    public void delete(Praise praise) {
        super.delete(praise);
    }

}