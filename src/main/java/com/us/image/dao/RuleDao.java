package com.us.image.dao;

import com.us.common.persistence.CrudDao;
import com.us.common.persistence.annotation.MyBatisDao;
import com.us.image.entities.Rule;

/**
 * Created by jansing on 16-11-9.
 */
@MyBatisDao
public interface RuleDao extends CrudDao<Rule> {

}
