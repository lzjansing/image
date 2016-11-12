package com.us.spring.mvc.utils;

import java.beans.PropertyEditorSupport;


/**
 * Created by jansing on 16-10-29.
 */
public class MyIntegerEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || "".equals(text)) {
            text = "0";
        } else {
            //true  -> 1
            //false -> 0
            try {
                text = new MyBooleanEditor().parse(text) ? "1" : "0";
            } catch (Exception e) {
            }
        }
        setValue(Integer.parseInt(text));
    }
}
