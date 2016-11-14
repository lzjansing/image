package com.us.common.modules.sys.dao;

import com.us.common.modules.sys.entities.Dict;
import com.us.common.persistence.CrudDao;
import com.us.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * Created by jansing on 16-11-13.
 */
@MyBatisDao
public interface DictDao extends CrudDao<Dict> {
    List<String> findTypeList(Dict dict);
}