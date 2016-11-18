package com.us.common.modules.sys.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.us.common.config.Global;
import com.us.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 结构：
 * root
 * ---模块
 * ---细分功能
 * ---权限
 * Created by jansing on 16-11-15.
 */
public class Menu extends DataEntity<Menu> {
    private Menu parent;
    private String pids;
    private String name;
    private String href;
    private String target;
    private String icon;
    private Integer sort = Integer.valueOf(30);
    private Integer isShow = YES;
    private String permission;
    //作查询条件用
    private User user;

    public Menu() {
    }

    public Menu(String id) {
        this.id = id;
    }

    public Menu(Menu parent) {
        this.parent = parent;
    }

    @JsonBackReference
    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    @Length(min = 0, max = 200)
    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    @Length(min = 1, max = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 0, max = 2000)
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Length(min = 0, max = 20)
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * 根据cascade过滤sourcelist
     * if(cascade)
     * 取parentId为第一层紧接的3层，比如parentId=root.id
     * root
     * ---e1
     * ---child1
     * ---child2
     * ---child3
     * ---e2
     * ---child1
     * ---child2
     * ---child3
     * else
     * 取parentId为第一层紧接的2层，比如parentId=root.id
     * root
     * ---e1
     * ---e2
     * ---e3
     *
     * @param list
     * @param sourcelist
     * @param parentId
     * @param cascade
     */
    @JsonIgnore
    public static void sortList(List<Menu> list, List<Menu> sourcelist, String parentId, boolean cascade) {
        for (int i = 0; i < sourcelist.size(); ++i) {
            Menu e = sourcelist.get(i);
            if (e.getParent() != null && e.getParent().getId() != null && e.getParent().getId().equals(parentId)) {
                list.add(e);
                if (cascade) {
                    for (int j = 0; j < sourcelist.size(); ++j) {
                        Menu child = sourcelist.get(j);
                        if (child.getParent() != null && child.getParent().getId() != null && child.getParent().getId().equals(e.getId())) {
                            sortList(list, sourcelist, e.getId(), true);
                            break;
                        }
                    }
                }
            }
        }

    }

    @JsonIgnore
    public static String getRootId() {
        return Global.getRootMenuId();
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
