package com.us.common.modules.sys.security;

import java.io.Serializable;

/**
 * Created by jansing on 16-11-20.
 */
public class UrlConfig implements Serializable {
    private String homeUrl = "/";
    private String loginUrl = "/login";
    private String successUrl = homeUrl;
    private String logoutUrl = "/logout";

    public UrlConfig() {
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        if (this.successUrl.equals(this.homeUrl)) {
            this.successUrl = homeUrl;
        }
        this.homeUrl = homeUrl;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }
}
