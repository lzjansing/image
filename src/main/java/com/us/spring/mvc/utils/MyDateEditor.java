package com.us.spring.mvc.utils;

import com.us.common.utils.DateUtil;

import java.beans.PropertyEditorSupport;

/**
 * Created by jansing on 16-10-30.
 */
public class MyDateEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        this.setValue(DateUtil.parseDate(text.trim()));
    }
}
