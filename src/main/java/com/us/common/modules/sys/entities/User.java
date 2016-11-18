package com.us.common.modules.sys.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.us.common.persistence.DataEntity;
import com.us.common.utils.StringUtil;
import org.hibernate.validator.constraints.Length;

import java.util.Iterator;
import java.util.List;

/**
 * Created by jansing on 16-11-6.
 */
public class User extends DataEntity<User> {
    private String username;
    private String password;
    private Integer userType;
    private Integer focus;
    private List<Role> roleList = Lists.newArrayList();
    public static final Integer USERTYPE_NORMAL = 1;
    public static final Integer USERTYPE_ADMIN = 2;

    //作查询条件用
    private Role role;

    public User() {
    }

    public User(String id) {
        super(id);
    }

    public User(String id, String username) {
        super(id);
        this.username = username;
    }

    public User(Role role) {
        this.role = role;
    }

    @Length(min = 5, max = 30, message = "用户名长度不符合，请输入5～30个字符的用户名")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    @Length(min = 5, max = 30, message = "密码长度不符合，请输入5～30个字符的密码")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用户类型
     * 普通用户 1
     * 管理员  2
     *
     * @return
     */
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 关注的用户数量
     *
     * @return
     */
    public Integer getFocus() {
        return focus;
    }

    public void setFocus(Integer focus) {
        this.focus = focus;
    }

    @JsonIgnore
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Boolean hasRole(Role role) {
        if (role == null || StringUtil.isBlank(role.getId())) {
            throw new IllegalArgumentException("角色为null");
        }
        for (Role r : roleList) {
            if (r.getId().equals(role.getId())) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    @JsonIgnore
    public List<String> getRoleIdList() {
        List<String> roleIdList = Lists.newArrayList();
        Iterator<Role> iterator = this.roleList.iterator();

        while (iterator.hasNext()) {
            roleIdList.add(iterator.next().getId());
        }

        return roleIdList;
    }

    public void setRoleIdList(List<String> roleIdList) {
        this.roleList = Lists.newArrayList();
        Iterator<String> iterator = roleIdList.iterator();

        while (iterator.hasNext()) {
            this.roleList.add(new Role(iterator.next()));
        }

    }

    //todo
//    public String getRoleNames() {
//        return Collections3.extractToString(this.roleList, "name", ",");
//    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userType=" + userType +
                ", focus=" + focus +
                '}';
    }
}
