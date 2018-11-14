package com.wmy.mpt.BaseSpringTest;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.wmy.mpt.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring.xml","classpath:spring/spring-*.xml"})
public class BaseSpringJunitTest {

    @Resource
    public UserService userService;

    public void system(List<?> list){
        System.out.println(JSON.toJSONString(list));
    }

    public void systemPage(Page<?> list){
        System.out.println(JSON.toJSONString(list));
    }

    public void systemSingle(Object o){
        System.out.println(JSON.toJSONString(o));
    }

    public void systemArgs(String id){
        System.out.println(id);
    }
}
