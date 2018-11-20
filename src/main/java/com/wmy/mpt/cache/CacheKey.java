package com.wmy.mpt.cache;
/*缓存key值前缀*/
public interface CacheKey {

    String SINGLE_USER_NAME = "single_user_name_";
    String ALL_USER_NAME = "all_user_name_";

    /*可以继续添加，用在不同的地方*/
}
