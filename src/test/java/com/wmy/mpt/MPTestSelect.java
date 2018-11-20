package com.wmy.mpt;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.wmy.mpt.BaseSpringTest.BaseSpringJunitTest;
import com.wmy.mpt.model.User;
import com.wmy.mpt.service.UserService;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.util.LinkedCaseInsensitiveMap;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MPTestSelect extends BaseSpringJunitTest {

    @Resource
    UserService userService;

    @Test
    /*查询所有 MP*/
    public void testAll(){
        List<User> userList = userService.selectList(null);
       super.system(userList);
    }

    @Test
    /*selectById  主键为id 参数只能是序列化的参数，String 不可以,MP*/
    public void testById(){
        User user = userService.selectById("1");
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    /*通过map形式传参，map中不能模糊查询 MP*/
    public void testByMap(){
        Map<String,Object> condition = new HashMap<>();
        condition.put("name","名字");
        condition.put("nickname","昵称");
        condition.put("number","158464");
        List<User> userList = userService.selectByMap(condition);
        System.out.println(JSON.toJSONString(userList));
    }

    @Test
    /*模糊查询 返回map格式，但是不是map格式 MP*/
    public void testMapByLike(){
        Wrapper<User> userWrapper = new EntityWrapper<>();
        userWrapper.like("password","密码");
        List<Map<String, Object>> userList = userService.selectMaps(userWrapper);
        System.out.println(JSON.toJSONString(userList));
    }

    @Test
    /*通过单个字符串查找数据 MP*/
    public void testByString(){
        User u = userService.selectOne(new EntityWrapper<User>().eq("email","158@qq.com"));
        super.systemSingle(u);
    }

    @Test
    /*不建议使用mp分页 ，
    {"asc":true,"condition":{},"current":1,"limit":2147483647,"offset":0,"offsetCurrent":0,"openSort":true,"optimizeCount":false,"pages":2,
    "records":[{"createTime":1501351825000,"email":"158@qq.com","id":"1","lastLoginTime":1501438232000,"name":"名字","nickname":"昵称","number":"158464","password":"密文密码","status":0},{"createTime":1501439211000,"email":"154@qq.com","id":"2","lastLoginTime":1501439209000,"name":"测试","nickname":"测试昵称","number":"456","password":"测试密码","status":1}],"searchCount":true,"size":2,"total":3}
    */
    public void testPage(){
        Page<User> userIPage = new Page<User>(0,2);
        Page<User> userList =  userService.selectPage(userIPage);
        systemPage(userList);
    }

    /*-------------------------------------------------------------------------------------------------------------*/

    @Test
    /*手动编写，参数map,单个数据 接收类型map*/
    public void testSelectByMap() throws InterruptedException {
        Map<String,String> map = new LinkedCaseInsensitiveMap<>();
        map.put("email","qq.com");
        map.put("password","密码");
        /*map.put("name","张三");*/
        List<Map<String,Object>> userList = userService.selectByMapOneself(map);
        super.system(userList);
        Thread.sleep(2000);
        List<Map<String,Object>> userListTwo = userService.selectByMapOneself(map);
        super.system(userListTwo);
    }

    /*简单的缓存测试*/
    @Test
    public void getTimestampTest() throws InterruptedException{
        System.out.println("第一次调用：" + userService.getTimestamp("param",1));
        Thread.sleep(2000);
        System.out.println("2秒之后调用：" + userService.getTimestamp("param",1));
        Thread.sleep(11000);
        System.out.println("再过11秒之后调用：" + userService.getTimestamp("param",1));
    }

    @Test
    public void getTimestampTestBymap() throws InterruptedException{
        Map<String,String> map = new HashMap<>();
        map.put("param","param");
        System.out.println("第一次调用：" + userService.getTimestampBymap(map));
        Thread.sleep(2000);
        System.out.println("2秒之后调用：" + userService.getTimestampBymap(map));
        Thread.sleep(11000);
        System.out.println("再过11秒之后调用：" + userService.getTimestampBymap(map));
    }



}
