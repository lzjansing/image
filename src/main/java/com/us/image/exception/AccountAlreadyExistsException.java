package com.us.image.exception;

import org.apache.shiro.authc.AccountException;

/**
 * @author : Zhong Junbin
 * @email : <a href="mailto:rekadowney@163.com">发送邮件</a>
 * @createDate : 2016/11/20 19:32
 * @description :
 */
public class AccountAlreadyExistsException extends AccountException {

    public AccountAlreadyExistsException() {
    }

    public AccountAlreadyExistsException(String message) {
        super(message);
    }

    public AccountAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public AccountAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
