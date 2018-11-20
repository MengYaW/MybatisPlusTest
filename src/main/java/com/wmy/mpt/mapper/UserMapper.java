package com.wmy.mpt.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wmy.mpt.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User>{
    List<Map<String,Object>> selectByMapOneSelf(Map<String, String> map);

    void insertMap(Map<String, Object> mapS);

    void insertB(List<Map<String, String>> maps);

    void updateMap(@Param("list") List<Map<String, Object>> maps, @Param("email") String email);

    void deleteByMapOnseSelf(Map<String, Object> condition);

    Map<String,Object> singleSelectByMap(Map<String, Object> map);
}
