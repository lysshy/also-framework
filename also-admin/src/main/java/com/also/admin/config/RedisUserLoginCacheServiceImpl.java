package com.also.admin.config;

import com.also.framework.cache.redis.RedisHelper;
import com.also.framework.security.jwt.config.cache.UserLoginCacheService;

public class RedisUserLoginCacheServiceImpl implements UserLoginCacheService {

    private final RedisHelper redisHelper;

    public RedisUserLoginCacheServiceImpl(RedisHelper redisHelper) {
        this.redisHelper = redisHelper;
    }

    @Override
    public String get(String username) {
        return redisHelper.syncGet(username);
    }

    @Override
    public void put(String username, String jwt) {
        redisHelper.syncSet(username, jwt);
    }
}
