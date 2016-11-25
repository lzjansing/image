package com.us.common.modules.sys.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by jansing on 16-11-14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/spring-context*.xml")
public class DictServiceTest {
    @Autowired
    private DictService dictService;

    @Test
    public void findTypeListTest(){
        List<String> list = dictService.findTypeList();
        System.out.println(list.size());
    }

}
