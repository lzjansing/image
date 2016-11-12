package com.us.image.dao;

import com.us.common.persistence.CrudDao;
import com.us.common.persistence.annotation.MyBatisDao;
import com.us.image.entities.Share;

/**
 * Created by jansing on 16-11-6.
 */
@MyBatisDao
public interface ShareDao extends CrudDao<Share> {

}
