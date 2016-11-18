package com.us.common.modules.sys.dao;

import com.us.common.modules.sys.entities.Menu;
import com.us.common.persistence.CrudDao;
import com.us.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * Created by jansing on 16-11-16.
 */
@MyBatisDao
public interface MenuDao extends CrudDao<Menu> {
    List<Menu> findByParentIdsLike(Menu menu);

    List<Menu> findByUserId(Menu menu);

    void updateParentIds(Menu menu);

    void updateSort(Menu menu);
}
