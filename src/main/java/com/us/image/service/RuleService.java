package com.us.image.service;

import com.us.common.persistence.Page;
import com.us.common.service.CrudService;
import com.us.image.dao.RuleDao;
import com.us.image.entities.Rule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by jansing on 16-11-9.
 */
@Service
@Transactional(readOnly = true)
public class RuleService extends CrudService<RuleDao, Rule> {

    public Rule get(String id) {
        return super.get(id);
    }

    public List<Rule> findList(Rule rule) {
        return super.findList(rule);
    }

    public Page<Rule> findPage(Page<Rule> page, Rule rule) {
        return super.findPage(page, rule);
    }

    @Transactional(readOnly = false)
    public void save(Rule rule) {
        super.save(rule);
    }

    @Transactional(readOnly = false)
    public void delete(Rule rule) {
        super.delete(rule);
    }

    @Transactional(readOnly = false)
    public void disable(Rule rule) {
        dao.disable(rule);
    }

    @Transactional(readOnly = false)
    public void enable(Rule rule) {
        dao.enable(rule);
    }

}