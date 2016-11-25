package com.us.common.service;

import com.us.common.persistence.CrudDao;
import com.us.common.persistence.DataEntity;
import com.us.common.persistence.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jansing on 16-11-6.
 */
@Transactional(readOnly = true)
public abstract class CrudService<D extends CrudDao<T>, T extends DataEntity<T>> extends BaseService {
    @Autowired
    protected D dao;

    public T get(String id) {
        return this.dao.get(id);
    }

//    public T get(T entity) {
//        return this.dao.get(entity);
//    }

    public List<T> findList(T entity) {
        return this.dao.findList(entity);
    }

    public Page<T> findPage(Page<T> page, T entity) {
        entity.setPage(page);
        page.setList(this.dao.findList(entity));
        return page;
    }

    @Transactional(readOnly = false)
    public void save(T entity) {
        if (entity.getId() == null) {
            entity.preInsert();
            this.dao.insert(entity);
        } else {
            entity.preUpdate();
            this.dao.update(entity);
        }

    }

    @Transactional(readOnly = false)
    public void delete(T entity) {
        this.dao.delete(entity);
    }
}
