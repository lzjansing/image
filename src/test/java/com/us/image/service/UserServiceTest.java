package com.us.image.service;

import com.us.common.persistence.Page;
import com.us.image.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by jansing on 16-11-6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-context.xml")
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void saveUser() {
//        for(int i=0; i<100; i++) {
            User user = new User();
        user.setUsername("2hx4");
        user.setCreateDate(new Date());
        user.setFocus(0);
        user.setPassword("fdsa");
//        user.setUserType(1);
            user.setValid(1);
            userService.save(user);
//        }
    }

    @Test
    public void getUser(){
        User user = userService.get("0c3cb1375be54ed28ee00fc9f66c35ad");
        System.out.println(user);

    }

    @Test
    public void update(){
        User user = userService.get("0c3cb1375be54ed28ee00fc9f66c35ad");
        user.setUsername("gyh");
        user.setCreateDate(new Date());
        userService.save(user);
    }

    @Test
    public void delete(){
        userService.delete(userService.get("0c3cb1375be54ed28ee00fc9f66c35ad"));
    }

    @Test
    public void disable(){
        userService.disable(userService.get("0c3cb1375be54ed28ee00fc9f66c35ad"));
    }

    @Test
    public void findList(){
        User user = new User();
        user.setPage(new Page<>());
        user.getPage().setOrderBy("id");
        List<User> list = userService.findList(user);
        System.out.println(list.size());
//        for(User u : list){
//            System.out.println(u.getId());
//        }
    }

    @Test
    public void findPage(){
        User user = new User();
        user.setPage(new Page<>());
        user.getPage().setOrderBy("id");
        user.getPage().setPageNo(1);
        user.getPage().setPageSize(10);
        Page<User> page = userService.findPage(user.getPage(), user);
        System.out.println(page.getCount());
        System.out.println(page.getTotalPage());
//        for(User u : list){
//            System.out.println(u.getId());
//        }
    }
}
