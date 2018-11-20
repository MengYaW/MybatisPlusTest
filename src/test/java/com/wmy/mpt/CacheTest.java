package com.wmy.mpt;

import com.wmy.mpt.BaseSpringTest.BaseSpringJunitTest;
import org.junit.Test;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheTest extends BaseSpringJunitTest {

    /*
    * 在@Cacheable 和 @CachePut  结合使用时，必须要注意
    * @CachePut得返回类型要和@Cacheable保持一致 ，key值保持一致，否则会得不到结果
    */
    @Test
    public void insertcacheTest() throws InterruptedException {
        Map<String,Object> mapS = new LinkedCaseInsensitiveMap<>();
        mapS.put("id","09");
        mapS.put("name","手动查入");
        mapS.put("nickname","map");
        mapS.put("email","198@qq.com");
        mapS.put("number","134567");
        mapS.put("password","1234568");
        mapS.put("createtime","2018-09-13 10:53:43");
        mapS.put("status","0");
        this.userService.inseryMap(mapS);

        Thread.sleep(5000);

        Map<String,Object> map = new LinkedCaseInsensitiveMap<>();
        map.put("email","qq.com");
        map.put("password","1234568");
        Map<String,Object> user = this.userService.singleSelectByMap(map);
        super.systemSingle(user);
    }

    @Test
    public void updatecacheTest() throws InterruptedException {
        List<Map<String,Object>> maps = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            Map<String,Object> map = new LinkedCaseInsensitiveMap<>();
            map.put("password","22222222");
            maps.add(map);
        }
        this.userService.updateMap(maps,"q.com");

        Thread.sleep(5000);

        Map<String,String> map = new LinkedCaseInsensitiveMap<>();
       // map.put("email","qq.com");
        map.put("password","22222222");
        List<Map<String,Object>> userList = userService.selectByMapOneself(map);
        super.system(userList);

        Thread.sleep(5000);
        List<Map<String,Object>> userLists = userService.selectByMapOneself(map);
        super.system(userLists);

    }

    @Test
    public void deletecacheTest() throws InterruptedException {
        Map<String,String> map = new LinkedCaseInsensitiveMap<>();
       // map.put("email","qq.com");
        map.put("password","22222222");
        List<Map<String,Object>> userLists = userService.selectByMapOneself(map);
        super.system(userLists);

        Thread.sleep(5000);

        List<Map<String,Object>> userListss = userService.selectByMapOneself(map);
        super.system(userListss);

        Thread.sleep(5000);

        Map<String,Object> condition = new HashMap<>();
        condition.put("password","22222222");
        this.userService.deleteByMapOneSelf(condition);

        Thread.sleep(5000);

        List<Map<String,Object>> userList = userService.selectByMapOneself(map);
        super.system(userList);
    }
}
