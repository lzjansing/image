package com.us.sys.utils;

import com.us.common.utils.SpringContextHolder;
import com.us.image.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jansing on 16-11-9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring-context.xml")
public class UserUtilTest {
    @Test
    public void get(){
//        User user = UserUtil.get("0c3cb1375be54ed28ee00fc9f66c35ad");
//        System.out.println(user);
        System.out.println((char)95);
    }
}
