package com.us.spring.mvc.utils;

import java.beans.PropertyEditorSupport;


/**
 * Created by jansing on 16-10-29.
 */
public class MyIntegerEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        if (text == null || "".equals(text)) {
            //默认设null，如果需要，请手动设0

        } else {
            try {
                //true  -> 1
                //false -> 0
                text = new MyBooleanEditor().parse(text) ? "1" : "0";
            } catch (Exception e) {
            }
            setValue(Integer.parseInt(text));
        }
    }
}
