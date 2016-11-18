package com.us.common.modules.sys.entities;

import com.google.common.collect.Lists;
import com.us.common.persistence.DataEntity;
import com.us.common.utils.StringUtil;
import org.hibernate.validator.constraints.Length;

import java.util.Iterator;
import java.util.List;

/**
 * Created by jansing on 16-11-15.
 */
public class Role extends DataEntity<Role> {
    private String name;
    private String enname;
    private Integer useable = YES;
    private List<Menu> menuList = Lists.newArrayList();
    //作查询条件用
    private User user;

    public Role() {
    }

    public Role(String id) {
        this();
        this.id = id;
    }

    public Role(User user) {
        this();
        this.user = user;
    }

    @Length(min = 1, max = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 1, max = 100)
    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public Integer getUseable() {
        return useable;
    }

    public void setUseable(Integer useable) {
        this.useable = useable;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getPermissions() {
        List<String> permissions = Lists.newArrayList();
        Iterator<Menu> iterator = this.menuList.iterator();

        while (iterator.hasNext()) {
            Menu menu = iterator.next();
            if (StringUtil.isNotBlank(menu.getPermission())) {
                permissions.add(menu.getPermission());
            }
        }

        return permissions;
    }

    public List<String> getMenuIdList() {
        List<String> menuIdList = Lists.newArrayList();
        Iterator<Menu> iterator = this.menuList.iterator();

        while (iterator.hasNext()) {
            Menu menu = iterator.next();
            menuIdList.add(menu.getId());
        }

        return menuIdList;
    }

    public void setMenuIdList(List<String> menuIdList) {
        this.menuList = Lists.newArrayList();
        Iterator<String> var2 = menuIdList.iterator();

        while (var2.hasNext()) {
            String menuId = var2.next();
            Menu menu = new Menu();
            menu.setId(menuId);
            this.menuList.add(menu);
        }

    }

    public String getMenuIds() {
        return StringUtil.join(this.getMenuIdList(), ",");
    }

    public void setMenuIds(String menuIds) {
        this.menuList = Lists.newArrayList();
        if (menuIds != null) {
            String[] ids = StringUtil.split(menuIds, ",");
            this.setMenuIdList(Lists.newArrayList(ids));
        }

    }
}
