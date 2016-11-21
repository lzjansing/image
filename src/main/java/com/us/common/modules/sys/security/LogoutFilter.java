package com.us.common.modules.sys.security;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.List;

/**
 * Created by jansing on 16-11-20.
 */
@Service
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {
    private static final Logger log = LoggerFactory.getLogger(LogoutFilter.class);
    protected PatternMatcher pathMatcher = new AntPathMatcher();
    private UrlConfig defaultUrlConfig;
    private List<UrlConfig> urlConfigList;

    public UrlConfig getDefaultUrlConfig() {
        return defaultUrlConfig;
    }

    public void setDefaultUrlConfig(UrlConfig defaultUrlConfig) {
        this.defaultUrlConfig = defaultUrlConfig;
    }

    public List<UrlConfig> getUrlConfigList() {
        return urlConfigList;
    }

    public void setUrlConfigList(List<UrlConfig> urlConfigList) {
        this.urlConfigList = urlConfigList;
    }

    protected String getPathWithinApplication(ServletRequest request) {
        return WebUtils.getPathWithinApplication(WebUtils.toHttp(request));
    }

    protected boolean pathsMatch(String path, ServletRequest request) {
        String requestURI = this.getPathWithinApplication(request);
        log.trace("Attempting to match pattern \'{}\' with current requestURI \'{}\'...", path, requestURI);
        return this.pathsMatch(path, requestURI);
    }

    protected boolean pathsMatch(String pattern, String path) {
        return this.pathMatcher.matches(pattern, path);
    }

    protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
        for (UrlConfig urlConfig : getUrlConfigList()) {
            if (this.pathsMatch(urlConfig.getLogoutUrl(), request)) {
                return urlConfig.getHomeUrl();
            }
        }
        return getDefaultUrlConfig().getHomeUrl();
    }
}
