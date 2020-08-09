package com.also.framework.cache.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.resource.ClientResources;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class RedisClientPoolFactory extends BasePooledObjectFactory<RedisClient> {

    private final ClientResources clientResources;
    private final RedisURI redisURI;

    public RedisClientPoolFactory(ClientResources clientResources, RedisURI redisURI) {
        this.clientResources = clientResources;
        this.redisURI = redisURI;
    }

    @Override
    public RedisClient create() throws Exception {
        return RedisClient.create(clientResources, redisURI);
    }

    @Override
    public PooledObject<RedisClient> wrap(RedisClient obj) {
        return new DefaultPooledObject<>(obj);
    }

    @Override
    public void destroyObject(PooledObject<RedisClient> p) throws Exception {
        p.getObject().shutdown();
    }
}
