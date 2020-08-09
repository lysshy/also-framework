package com.also.framework.cache.redis;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RedisProperties {

    private String host;

    private int port = 6379;

    private String password;

    private long expireSecond = 60 * 60;
}
