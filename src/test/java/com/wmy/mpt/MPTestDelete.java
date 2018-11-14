package com.wmy.mpt;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.wmy.mpt.BaseSpringTest.BaseSpringJunitTest;
import com.wmy.mpt.model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MPTestDelete extends BaseSpringJunitTest {

    @Test
    public void testdeleteWrapper(){
        this.userService.delete(new EntityWrapper<User>().like("email","163.com"));
    }

    @Test
    public void testdeleteB(){
        List<Integer> ids = new ArrayList<>();
        ids.add(12);
        ids.add(123);
        this.userService.deleteBatchIds(ids);
    }

    @Test
    public void testdeleteMap(){
        Map<String,Object> condition = new HashMap<>();
        condition.put("name","手动查入");
        condition.put("nickname","map");
        condition.put("number","134567");
        this.userService.deleteByMap(condition);

    }

    /*-----------------------------------------------------------------------------------------------------------------*/

    @Test
    public void testdeleteByMap(){
        Map<String,Object> condition = new HashMap<>();
        condition.put("name","测试");
        condition.put("nickname","");
        this.userService.deleteByMapOneSelf(condition);
    }
}
