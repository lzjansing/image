package com.us.common.modules.sys.security;

import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.springframework.beans.BeansException;

import javax.servlet.Filter;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jansing on 16-11-19.
 */
public class ShiroFilterFactoryBean extends org.apache.shiro.spring.web.ShiroFilterFactoryBean {
    private String frontLoginUrl;
    private String frontSuccessUrl;

    public String getFrontLoginUrl() {
        return frontLoginUrl;
    }

    public void setFrontLoginUrl(String frontLoginUrl) {
        this.frontLoginUrl = frontLoginUrl;
    }

    public String getFrontSuccessUrl() {
        return frontSuccessUrl;
    }

    public void setFrontSuccessUrl(String frontSuccessUrl) {
        this.frontSuccessUrl = frontSuccessUrl;
    }

    protected FilterChainManager createFilterChainManager() {
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

    private void applyFrontLoginUrlIfNecessary(Filter filter) {
        String frontLoginUrl = this.getFrontLoginUrl();
        if(StringUtils.hasText(frontLoginUrl) && filter instanceof FormAuthenticationFilter) {
            FormAuthenticationFilter formFilter = (FormAuthenticationFilter)filter;
            String existingFrontLoginUrl = formFilter.getFrontLoginUrl();
            if(FormAuthenticationFilter.DEFAULT_FRONT_LOGIN_URL.equals(existingFrontLoginUrl)) {
                formFilter.setFrontLoginUrl(frontLoginUrl);
            }
        }

    }

    private void applyFrontSuccessUrlIfNecessary(Filter filter) {
        String frontSuccessUrl = this.getFrontSuccessUrl();
        if(StringUtils.hasText(frontSuccessUrl) && filter instanceof FormAuthenticationFilter) {
            FormAuthenticationFilter formFilter = (FormAuthenticationFilter)filter;
            String existingFrontSuccessUrl = formFilter.getFrontSuccessUrl();
            if(FormAuthenticationFilter.DEFAULT_FRONT_SUCCESS_URL.equals(existingFrontSuccessUrl)) {
                formFilter.setFrontSuccessUrl(frontSuccessUrl);
            }
        }

    }

    private void applyGlobalPropertiesIfNecessary(Filter filter){
        this.applyFrontLoginUrlIfNecessary(filter);
        this.applyFrontSuccessUrlIfNecessary(filter);
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
