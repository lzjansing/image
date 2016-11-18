package com.us.common.modules.sys.dao;

import com.us.common.modules.sys.entities.Role;
import com.us.common.persistence.CrudDao;
import com.us.common.persistence.annotation.MyBatisDao;

/**
 * Created by jansing on 16-11-16.
 */
@MyBatisDao
public interface RoleDao extends CrudDao<Role> {
    Role getByName(Role var1);

    Role getByEnname(Role var1);

    int deleteRoleMenu(Role var1);

    int insertRoleMenu(Role var1);
}

