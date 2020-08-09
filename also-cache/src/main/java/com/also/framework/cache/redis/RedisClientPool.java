package com.also.framework.cache.redis;

import io.lettuce.core.RedisClient;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class RedisClientPool extends GenericObjectPool<RedisClient> {

    public RedisClientPool(PooledObjectFactory<RedisClient> factory, GenericObjectPoolConfig<RedisClient> config) {
        super(factory, config);
    }

    public String printDetail() {
        StringBuilder sb = new StringBuilder();
        toStringAppendFields(sb);
        return sb.toString();
    }
}
