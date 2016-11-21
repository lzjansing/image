package com.us.common.modules.sys.security;

/**
 * Created by jansing on 16-11-16.
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {
    private String captcha;
    private boolean mobileLogin;
    private boolean frontEnd;

    public UsernamePasswordToken() {
    }

    public UsernamePasswordToken(String username, char[] password) {
        super(username, password);
    }

    public UsernamePasswordToken(String username, char[] password, boolean rememberMe, String host, String captcha, boolean mobileLogin, boolean frontEnd) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
        this.mobileLogin = mobileLogin;
        this.frontEnd = frontEnd;
    }

    public String getCaptcha() {
        return this.captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public boolean isMobileLogin() {
        return this.mobileLogin;
    }

    public boolean isFrontEnd() {
        return frontEnd;
    }

    public void setFrontEnd(boolean frontEnd) {
        frontEnd = frontEnd;
    }
}
