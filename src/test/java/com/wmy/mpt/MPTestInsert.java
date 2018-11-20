package com.wmy.mpt;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import com.wmy.mpt.BaseSpringTest.BaseSpringJunitTest;
import com.wmy.mpt.model.User;
import com.wmy.mpt.service.UserService;
import org.junit.Test;
import org.springframework.util.LinkedCaseInsensitiveMap;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MPTestInsert extends BaseSpringJunitTest{

    @Resource
    private UserService userService;

    @Test
    /*通过对象插入，插入成功后，自动返回主键 MP*/
    public void testInsert(){
        User u = new User();
        u.setId("12");
        u.setName("王五");
        u.setNickname("小五");
        u.setEmail("198@qq.com");
        u.setNumber("123456");
        u.setCreateTime(new Date());
        u.setPassword("111112");
        userService.insert(u);
        systemArgs(u.getId());
    }

    @Test
    /*批量插入，MP*/
    public void testInsertB(){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            User u = new User();
            u.setId(String.valueOf(5+i));
            u.setPassword("12345"+i);
            u.setCreateTime(new Date());
            u.setName("测试"+i);
            u.setNickname("cs"+i);
            u.setNumber("123"+i);
            u.setEmail(String.valueOf(120+i)+"@qq.com");
            u.setStatus(0);
            userList.add(u);
        }
        userService.insertBatch(userList);
    }

    @Test
    /*通过主键 判断数据是否存在，存在更新，不存在插入*/
    public void testInsetOrUpdate(){
        User u = new User();
        u.setId("5");
        u.setNumber("177");
        userService.insertOrUpdate(u);
    }

    /*------------------------------------------------------------------------------------------------------------------*/
    @Test
    /*单个插入*/
    public void testMapInsert(){
        Map<String,Object> mapS = new LinkedCaseInsensitiveMap<>();
        mapS.put("id","09");
        mapS.put("name","手动查入");
        mapS.put("nickname","map");
        mapS.put("email","198@qq.com");
        mapS.put("number","134567");
        mapS.put("password","1234568");
        mapS.put("createtime","2018-09-13 10:53:43");
        mapS.put("status","0");
        userService.inseryMap(mapS);
    }

    /*批量插入*/
    @Test
    public void insertB(){
        List<Map<String,String>> maps = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            Map<String,String> map = new LinkedCaseInsensitiveMap<>();
            map.put("id","05"+i);
            map.put("name","map"+i);
            map.put("nickname","m"+i);
            map.put("email",String.valueOf(150+i)+"@163.com");
            map.put("number","12789"+i);
            map.put("password","111111");
            map.put("createtime","2018-11-13 14:37:45");
            map.put("status","0");
            maps.add(map);
        }
        userService.insertB(maps);
    }
}