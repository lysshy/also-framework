package com.also.framework.cache.redis;


import com.also.framework.cache.utils.RedisClientUtils;
import io.lettuce.core.RedisClient;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisHelper {

    private final RedisClientPool redisClientPool;

    public RedisHelper(RedisClientPool redisClientPool) {
        this.redisClientPool = redisClientPool;
    }

    public boolean syncSet(String key, String value, long expireSecond) {
        RedisClient redisClient = null;
        try {
            redisClient = redisClientPool.borrowObject();
            return RedisClientUtils.syncSet(redisClient, key, value, expireSecond);
        } catch (Exception e) {
            log.error("sync set error.", e);
            return false;
        } finally {
            returnClient(redisClient);
        }
    }

    public boolean syncSet(String key, String value) {
        return syncSet(key, value, RedisClientUtils.NO_EXPIRE);
    }

    public boolean syncSetNX(String key, String value, long expireSecond) throws Exception {
        RedisClient redisClient = null;
        try {
            redisClient = redisClientPool.borrowObject();
            return RedisClientUtils.syncSetNX(redisClient, key, value, expireSecond);
        } finally {
            returnClient(redisClient);
        }
    }

    public boolean syncSetNX(String key, String value) throws Exception {
        return syncSetNX(key, value, RedisClientUtils.NO_EXPIRE);
    }

    public boolean syncSetXX(String key, String value, long expireSecond) throws Exception {
        RedisClient redisClient = null;
        try {
            redisClient = redisClientPool.borrowObject();
            return RedisClientUtils.syncSetXX(redisClient, key, value, expireSecond);
        } finally {
            returnClient(redisClient);
        }
    }

    public boolean syncSetXX(String key, String value) throws Exception {
        return syncSetXX(key, value, RedisClientUtils.NO_EXPIRE);
    }

    public String syncGet(String key) {
        RedisClient redisClient = null;
        try {
            redisClient = redisClientPool.borrowObject();
            if (log.isDebugEnabled()) {
                log.debug(redisClientPool.printDetail());
            }
            return RedisClientUtils.syncGet(redisClient, key);
        } catch (Exception e) {
            log.error("sync get error.", e);
            return null;
        } finally {
            returnClient(redisClient);
        }
    }

    public long delete(String... keys) throws Exception {
        RedisClient redisClient = null;
        try {
            redisClient = redisClientPool.borrowObject();
            if (log.isDebugEnabled()) {
                log.debug(redisClientPool.printDetail());
            }
            return RedisClientUtils.delete(redisClient, keys);
        } finally {
            returnClient(redisClient);
        }
    }

    public RedisClient getClient() throws Exception {
        return redisClientPool.borrowObject();
    }

    public void returnClient(RedisClient redisClient) {
        if (redisClient != null) {
            redisClientPool.returnObject(redisClient);
        }
    }
}
