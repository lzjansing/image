package com.us.image.enumeration;

import java.util.NoSuchElementException;

/**
 * @author : Chung Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2016-06-29 20:03
 * @description :
 */
public enum BloodGroup {

    A("A血型"), B("B血型"), AB("AB血型"), O("O血型");

    private final String name;

    BloodGroup(String name) {
        this.name = name;
    }

    public static BloodGroup parse(String name) {
        for (BloodGroup bloodGroup : BloodGroup.values()) {
            if (bloodGroup.getName().equals(name)) {
                return bloodGroup;
            }
        }
        throw new NoSuchElementException("不存在[" + name + "]对应的枚举值");
    }

    public String getName() {
        return name;
    }

}
