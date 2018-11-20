package com.wmy.mpt.service;

import com.baomidou.mybatisplus.service.IService;
import com.wmy.mpt.model.User;

import java.util.List;
import java.util.Map;

public interface UserService extends IService<User> {

    List<Map<String,Object>> selectByMapOneself(Map<String, String> map);

    void inseryMap(Map<String, String> mapS);

    void insertB(List<Map<String, String>> maps);

    void updateMap(List<Map<String, String>> maps, String email);

    void deleteByMapOneSelf(Map<String, Object> condition);

    String getTimestamp(String param,Integer param2);

    String getTimestampBymap(Map<String, String> map);
}
