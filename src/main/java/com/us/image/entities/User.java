package com.us.image.entities;

import com.us.image.enumeration.BloodGroup;
import com.us.image.enumeration.Gender;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2016/11/20 18:30
 * @description :
 */
public class User implements Serializable {

    private String id;
    private String nickname;
    private String realName;
    private Gender gender;
    private BloodGroup bloodGroup;
    private LocalDate birthday;
    private String interests;
    private String blogAddress;
    private long qq;

    public User() {
    }

    public User(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", nickname='" + nickname + '\'' +
                ", realName='" + realName + '\'' +
                ", gender=" + gender +
                ", bloodGroup=" + bloodGroup +
                ", birthday=" + birthday +
                ", interests='" + interests + '\'' +
                ", blogAddress='" + blogAddress + '\'' +
                ", qq=" + qq +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return qq == user.qq &&
                Objects.equals(id, user.id) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(realName, user.realName) &&
                gender == user.gender &&
                bloodGroup == user.bloodGroup &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(interests, user.interests) &&
                Objects.equals(blogAddress, user.blogAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, realName, gender, bloodGroup, birthday, interests, blogAddress, qq);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getBlogAddress() {
        return blogAddress;
    }

    public void setBlogAddress(String blogAddress) {
        this.blogAddress = blogAddress;
    }

    public long getQq() {
        return qq;
    }

    public void setQq(long qq) {
        this.qq = qq;
    }

}
