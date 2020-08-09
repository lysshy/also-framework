package com.also.framework.cache.redis;

import io.lettuce.core.RedisClient;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class RedisClientPoolConfig extends GenericObjectPoolConfig<RedisClient> {

    public RedisClientPoolConfig(RedisPoolProperties redisPoolProperties) {
        setMaxIdle(redisPoolProperties.getMaxIdle());
        setMaxTotal(redisPoolProperties.getMaxTotal());
        setMinIdle(redisPoolProperties.getMinIdle());
        setJmxNamePrefix("also-redis-pool");
    }
}
