package com.us.image.enumeration;

import java.util.NoSuchElementException;

/**
 * @author : Chung Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2016-06-29 19:59
 * @description :
 */
public enum Gender {

    MALE("男"), FEMALE("女");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public static Gender parse(String name) {
        for (Gender gender : Gender.values()) {
            if (gender.getName().equals(name)) {
                return gender;
            }
        }
        throw new NoSuchElementException("不存在[" + name + "]对应的枚举值");
    }

    public String getName() {
        return name;
    }

}
