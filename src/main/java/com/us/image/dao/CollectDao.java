package com.us.image.dao;

import com.us.common.persistence.CrudDao;
import com.us.common.persistence.annotation.MyBatisDao;
import com.us.image.entities.Collect;

/**
 * Created by jansing on 16-11-6.
 */
@MyBatisDao
public interface CollectDao extends CrudDao<Collect> {

    public int deleteSelf(Collect collect);

    public int count(Collect collect);
}
