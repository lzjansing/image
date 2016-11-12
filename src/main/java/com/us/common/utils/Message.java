package com.us.common.utils;

/**
 * Created by jansing on 16-10-17.
 */
public class Message {

    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
