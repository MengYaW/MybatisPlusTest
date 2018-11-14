package com.wmy.mpt.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wmy.mpt.mapper.UserMapper;
import com.wmy.mpt.model.User;
import com.wmy.mpt.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public List<Map<String, Object>> selectByMapOneself(Map<String, String> map) {
        return userMapper.selectByMapOneSelf(map);
    }

    @Override
    public void inseryMap(Map<String, String> mapS) {
        userMapper.insertMap(mapS);
    }

    @Override
    public void insertB(List<Map<String, String>> maps) {
        userMapper.insertB(maps);
    }

    @Override
    public void updateMap(List<Map<String, String>> maps,String email) {
        userMapper.updateMap(maps,email);
    }

    @Override
    public void deleteByMapOneSelf(Map<String, Object> condition) {
        userMapper.deleteByMapOnseSelf(condition);
    }
}
