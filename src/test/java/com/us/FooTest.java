package com.us;

import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.util.PatternMatcher;
import org.apache.shiro.web.util.WebUtils;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Test
    public void test02(){
        PatternMatcher pathMatcher = new AntPathMatcher();
        System.out.println(pathMatcher.matches("123","1234"));
        System.out.println(pathMatcher.matches("1234","123"));
        System.out.println(pathMatcher.matches("123","123"));
    }

}
