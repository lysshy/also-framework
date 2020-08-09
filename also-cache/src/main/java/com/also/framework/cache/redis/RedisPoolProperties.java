package com.also.framework.cache.redis;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RedisPoolProperties {

    private int maxTotal = 10;

    private int maxIdle = 10;

    private int minIdle = 0;
}
