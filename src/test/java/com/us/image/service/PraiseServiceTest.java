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
public class PraiseServiceTest {
    @Autowired
    private PraiseService praiseService;


    @Test
    public void praisedTest(){
        praiseService.praised("05a731c88ec546239d9231054f9a47e0");
    }

    @Test
    public void uncollectedTest(){
        praiseService.unpraised("05a731c88ec546239d9231054f9a47e0");
    }

    @Test
    public void countCurrentUserTest(){
        System.out.println(praiseService.countCurrentUser());
    }

    @Test
    public void countNewCurrentUserTest(){
        System.out.println(praiseService.countNewCurrentUser());
    }

}
