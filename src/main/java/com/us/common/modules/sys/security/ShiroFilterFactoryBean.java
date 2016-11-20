package com.us.common.modules.sys.security;

import org.apache.commons.lang3.Validate;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jansing on 16-11-19.
 */
public class ShiroFilterFactoryBean extends org.apache.shiro.spring.web.ShiroFilterFactoryBean {
    private static final transient Logger log = LoggerFactory.getLogger(ShiroFilterFactoryBean.class);
    private Map<String, UrlConfig> urlConfigMap;
    private String defaultUrlConfigKey = "default";

    public Map<String, UrlConfig> getUrlConfigMap() {
        return urlConfigMap;
    }

    public void setUrlConfigMap(Map<String, UrlConfig> urlConfigMap) {
        this.urlConfigMap = urlConfigMap;
    }

    public String getDefaultUrlConfigKey() {
        return defaultUrlConfigKey;
    }

    public void setDefaultUrlConfigKey(String defaultUrlConfigKey) {
        this.defaultUrlConfigKey = defaultUrlConfigKey;
    }

    protected FilterChainManager createFilterChainManager() {
        Validate.validState(urlConfigMap!=null && !urlConfigMap.isEmpty(), "urlConfigMap属性未注入, 请在spring-context-shiro.xml中定义ShiroFilterFactoryBean.", new Object[0]);
        UrlConfig defaultConfig = getUrlConfigMap().get(defaultUrlConfigKey);
        Validate.validState(defaultConfig!=null, "未设置缺省的urlConfig, 请在spring-context-shiro.xml中定义ShiroFilterFactoryBean.", new Object[0]);
        super.setLoginUrl(defaultConfig.getLoginUrl());
        super.setSuccessUrl(defaultConfig.getSuccessUrl());
        FilterChainManager manager = super.createFilterChainManager();
        Map defaultFilters = manager.getFilters();
        Iterator filters = defaultFilters.values().iterator();

        while(filters.hasNext()) {
            Filter chains = (Filter)filters.next();
            this.applyGlobalPropertiesIfNecessary(chains);
        }

        Map filters1 = this.getFilters();
        String entry;
        Filter url;
        if(!CollectionUtils.isEmpty(filters1)) {
            for(Iterator chains1 = filters1.entrySet().iterator(); chains1.hasNext(); manager.addFilter(entry, url, false)) {
                Map.Entry i$ = (Map.Entry)chains1.next();
                entry = (String)i$.getKey();
                url = (Filter)i$.getValue();
                this.applyGlobalPropertiesIfNecessary(url);
                if(url instanceof Nameable) {
                    ((Nameable)url).setName(entry);
                }
            }
        }

        return manager;
    }

    private void applyUrlConfigIfNecessary(Filter filter){
        if(filter instanceof FormAuthenticationFilter) {
            FormAuthenticationFilter acFilter = (FormAuthenticationFilter)filter;
            acFilter.setUrlConfigList(new ArrayList<>(getUrlConfigMap().values()));
            acFilter.setDefaultUrlConfig(getUrlConfigMap().get(defaultUrlConfigKey));
        }else if(filter instanceof LogoutFilter){
            LogoutFilter outFilter = (LogoutFilter)filter;
            outFilter.setUrlConfigList(new ArrayList<>(getUrlConfigMap().values()));
            outFilter.setDefaultUrlConfig(getUrlConfigMap().get(defaultUrlConfigKey));
        }
    }

    private void applyGlobalPropertiesIfNecessary(Filter filter){
        this.applyUrlConfigIfNecessary(filter);
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof Filter) {
            super.postProcessBeforeInitialization(bean, beanName);
            this.applyGlobalPropertiesIfNecessary((Filter)bean);
        }

        return bean;
    }
}
