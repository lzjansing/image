package com.us.image.entities;

import com.us.common.modules.sys.utils.DictUtil;
import com.us.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * Created by jansing on 16-11-6.
 */
public class User extends DataEntity<User> {
    private String username;
    private String password;
    private Integer userType;
    private Integer focus;
    public static final Integer USERTYPE_NORMAL = Integer.valueOf(DictUtil.getDictValue("普通用户", "user_type", null));
    public static final Integer USERTYPE_ADMIN = Integer.valueOf(DictUtil.getDictValue("管理员", "user_type", null));

    public User() {
    }

    @Length(min = 5, max = 30, message = "用户名长度不符合，请输入5～30个字符的用户名")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", userType=" + userType +
                ", focus=" + focus +
                '}';
    }
}
