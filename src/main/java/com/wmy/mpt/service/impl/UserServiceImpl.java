package com.wmy.mpt.service.impl;

import com.wmy.mpt.cache.CacheKey;
import com.wmy.mpt.cache.CacheName;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.wmy.mpt.mapper.UserMapper;
import com.wmy.mpt.model.User;
import com.wmy.mpt.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    @Cacheable(value = CacheName.CACHENAME,key = "'"+ CacheKey.SINGLE_USER_NAME +"'+ # map.get('password')")
    public List<Map<String, Object>> selectByMapOneself(Map<String, String> map) {
        return userMapper.selectByMapOneSelf(map);
    }

    @Cacheable(value= CacheName.CACHENAME,key="#param")
    public String getTimestamp(String param,Integer param2) {
        Long timestamp = System.currentTimeMillis();
        param2 += 1;
        return timestamp.toString() + param2.toString();
    }

    @Cacheable(value= CacheName.CACHENAME,key="#map.get('param')")
    public String getTimestampBymap(Map<String, String> map) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }

    @Override
    @CachePut(value = CacheName.CACHENAME,key = "'"+ CacheKey.SINGLE_USER_NAME +"'+ #map.get('password')")
    public void inseryMap(Map<String, String> mapS) {
        userMapper.insertMap(mapS);
    }

    @Override
    public void insertB(List<Map<String, String>> maps) {
        userMapper.insertB(maps);
    }

    @Override
    @CacheEvict(value = CacheName.CACHENAME)
    public void updateMap(List<Map<String, String>> maps,String email) {
        userMapper.updateMap(maps,email);
    }

    @Override
    @CacheEvict(value = CacheName.CACHENAME)
    public void deleteByMapOneSelf(Map<String, Object> condition) {
        userMapper.deleteByMapOnseSelf(condition);
    }
}
