package com.us.image.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jansing on 16-11-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring-context*.xml")
public class FocusServiceTest {
    @Autowired
    private FocusService focusService;


    @Test
    public void focusedTest(){
        focusService.focused("835e00ac25a44faca4fc11074be86e9b");
    }

    @Test
    public void unfocusedTest(){
        focusService.unfocused("835e00ac25a44faca4fc11074be86e9b");
    }

    @Test
    public void countCurrentUserTest(){
        System.out.println(focusService.countCurrentUser());
    }

}
