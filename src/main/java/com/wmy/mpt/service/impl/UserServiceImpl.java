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
    @Cacheable(value = CacheName.CACHENAME,key = "'"+ CacheKey.ALL_USER_NAME +"'+ #map.get('password')")
    public List<Map<String, Object>> selectByMapOneself(Map<String, String> map) {
        return userMapper.selectByMapOneSelf(map);
    }

    @Override
    @Cacheable(value = CacheName.CACHENAME,key = "'"+ CacheKey.SINGLE_USER_NAME +"'+ #map.get('password')")
    public Map<String, Object> singleSelectByMap(Map<String, Object> map) {
        return userMapper.singleSelectByMap(map);
    }

    @Override
    @CachePut(value = CacheName.CACHENAME,key = "'"+ CacheKey.SINGLE_USER_NAME +"'+ #mapS.get('password')")
    public Map<String, Object> inseryMap(Map<String, Object> mapS) {
        userMapper.insertMap(mapS);
        return mapS;
    }

    @Override
    /*批量插入不适合做缓存*/
    public void insertB(List<Map<String, String>> maps) {
        userMapper.insertB(maps);
    }

    @Override
    @CacheEvict(value = CacheName.CACHENAME,allEntries = true)
    public void updateMap(List<Map<String, Object>> maps,String email) {
        userMapper.updateMap(maps,email);
    }

    @Override
    @CacheEvict(value = CacheName.CACHENAME,allEntries = true)
    public void deleteByMapOneSelf(Map<String, Object> condition) {
        userMapper.deleteByMapOnseSelf(condition);
    }

    /*------------------------------------简单的缓存测试--------------------------------------------*/

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
}
