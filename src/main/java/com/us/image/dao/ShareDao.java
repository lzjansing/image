package com.us.image.dao;

import com.us.common.persistence.CrudDao;
import com.us.common.persistence.annotation.MyBatisDao;
import com.us.image.entities.Share;

/**
 * Created by jansing on 16-11-6.
 */
@MyBatisDao
public interface ShareDao extends CrudDao<Share> {

    public int updateSelf(Share share);

    public int increase(Share share);

    public int decrease(Share share);

    public int deleteSelf(Share share);
}
