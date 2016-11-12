package com.us.spring.mvc.utils;

import org.springframework.beans.propertyeditors.CustomBooleanEditor;

/**
 * Created by jansing on 16-10-29.
 */
public class MyBooleanEditor extends CustomBooleanEditor {
    public MyBooleanEditor() {
        super(true);
    }

    public boolean parse(String text) {
        setAsText(text);
        return (boolean) getValue();
    }
}
