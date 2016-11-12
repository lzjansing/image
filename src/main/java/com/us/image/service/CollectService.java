package com.us.image.service;

import com.us.common.persistence.Page;
import com.us.common.service.CrudService;
import com.us.image.dao.CollectDao;
import com.us.image.entities.Collect;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jansing on 16-11-6.
 */
@Service
@Transactional(readOnly = true)
public class CollectService extends CrudService<CollectDao, Collect> {

    public Collect get(String id) {
        return super.get(id);
    }

    public List<Collect> findList(Collect collect) {
        return super.findList(collect);
    }

    public Page<Collect> findPage(Page<Collect> page, Collect collect) {
        return super.findPage(page, collect);
    }

    @Transactional(readOnly = false)
    public void save(Collect collect) {
        super.save(collect);
    }

    @Transactional(readOnly = false)
    public void delete(Collect collect) {
        super.delete(collect);
    }

}