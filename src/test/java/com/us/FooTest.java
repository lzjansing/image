package com.us;

import org.junit.Test;

/**
 * Created by jansing on 16-11-18.
 */
public class FooTest {


    @Test
    public void test01(){
        StringBuilder sb = new StringBuilder();
        sb.append("123").append("abc");
        System.out.println(sb);
    }

}
