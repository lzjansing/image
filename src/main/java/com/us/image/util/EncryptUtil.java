package com.us.image.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2016/11/20 19:54
 * @description :
 */
public class EncryptUtil {

    public static String encrypt(String encryptText, String salt) {
        Md5Hash md5Hash = new Md5Hash(encryptText, salt);
        return md5Hash.toString();
    }

}
