package com.us.spring.mvc.utils;

import com.us.common.utils.LocalDateTimeUtil;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;

/**
 * Created by jansing on 16-11-21.
 */
public class MyLocalDateTimeEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        this.setValue(LocalDateTimeUtil.parse(text));
    }

    @Override
    public String getAsText() {
        LocalDateTime obj = (LocalDateTime) this.getValue();
        return LocalDateTimeUtil.formatDateTime(obj);
    }
}
