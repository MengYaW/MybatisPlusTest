package com.wmy.mpt.service;

import com.baomidou.mybatisplus.service.IService;
import com.wmy.mpt.model.User;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    List<Map<String,Object>> selectByMapOneself(Map<String, String> map);

    Map<String, Object> inseryMap(Map<String, Object> mapS);

    void insertB(List<Map<String, String>> maps);

    void updateMap(List<Map<String, Object>> maps, String email);

    void deleteByMapOneSelf(Map<String, Object> condition);

    String getTimestamp(String param,Integer param2);

    String getTimestampBymap(Map<String, String> map);

    Map<String,Object> singleSelectByMap(Map<String, Object> map);
}
