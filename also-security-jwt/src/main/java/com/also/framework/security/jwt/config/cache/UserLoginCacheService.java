package com.also.framework.security.jwt.config.cache;

public interface UserLoginCacheService {

    String get(String username);

    void put(String username, String jwt);
}
