package com.us.image.controller;

import com.us.common.persistence.Page;
import com.us.image.entities.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by jansing on 16-11-25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring*.xml")
public class AccountControllerTest {
    @Autowired
    private AccountController accountController;


    @Test
    public void getCommentsTest(){
        Page<Comment> page = accountController.getComments("722675310bab48aa924be6ed231ccbe6", 1, 30);
        List<Comment> list = page.getList();
        System.out.println(page.getPageNo());
        System.out.println(page.getCount());
        System.out.println(page.getTotalPage());
        for(Comment c : list){
            System.out.println(c.getContent());
        }

    }
}
