package com.us.image.dao;

import com.us.common.persistence.CrudDao;
import com.us.common.persistence.annotation.MyBatisDao;
import com.us.image.entities.Focus;

/**
 * Created by jansing on 16-11-6.
 */
@MyBatisDao
public interface FocusDao extends CrudDao<Focus> {

    public int deleteSelf(Focus focus);

    public int count(Focus focus);

}
