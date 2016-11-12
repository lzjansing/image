package com.us.spring.mvc.utils;

import org.apache.commons.lang3.StringEscapeUtils;

import java.beans.PropertyEditorSupport;

/**
 * Created by jansing on 16-10-30.
 */
public class MyStringEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        //转义，防注入
        this.setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
    }

    @Override
    public String getAsText() {
        Object value = this.getValue();
        return value != null ? value.toString() : "";
    }
}
