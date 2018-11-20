package com.wmy.mpt;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wmy.mpt.BaseSpringTest.BaseSpringJunitTest;
import com.wmy.mpt.model.User;
import org.junit.Test;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class MPTestUpdate extends BaseSpringJunitTest {

    @Test
    public void testUpdate() {
        User u = new User();
        u.setStatus(0);
        //第一种更新方式
        //this.userService.update(u, new EntityWrapper<User>().eq("id", 12));
        //第二种更新方式
        u.setId("123");
        this.userService.updateById(u);
    }

    @Test
    /*批量更新  MP*/
    public void testUpdateBatchById(){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i <5; i++){
            User u = new User();
            u.setId(String.valueOf(5+i));
            u.setCreateTime(new Date());
            userList.add(u);
        }
        this.userService.updateBatchById(userList);
    }
    /*-----------------------------------------------------------------------------------------------------*/

    @Test
    /*通过模糊条件，map更新数据*/
    public void testUpdateMap(){
        List<Map<String,Object>> maps = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            Map<String,Object> map = new LinkedCaseInsensitiveMap<>();
            map.put("password","22222222");
            maps.add(map);
        }
        this.userService.updateMap(maps,"163.com");
    }
}
