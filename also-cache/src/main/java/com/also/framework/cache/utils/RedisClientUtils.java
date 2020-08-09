package com.also.framework.cache.utils;

import io.lettuce.core.RedisClient;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.protocol.RedisCommand;

public class RedisClientUtils {

    public static final long NO_EXPIRE = -1;

    private static final String SET_OK = "OK";

    public static String syncGet(RedisClient redisClient, String key) {
        try (StatefulRedisConnection<String, String> redisConnection = redisClient.connect()) {
            return redisConnection.sync().get(key);
        }
    }

    public static boolean syncSetNX(RedisClient redisClient, String key, String value, long expireSecond) {
        SetArgs setArgs = SetArgs.Builder.nx();
        if (expireSecond != NO_EXPIRE) {
            setArgs = setArgs.ex(expireSecond);
        }
        return syncSet(redisClient, key, value, setArgs);
    }

    public static boolean syncSetXX(RedisClient redisClient, String key, String value, long expireSecond) {
        SetArgs setArgs = SetArgs.Builder.xx();
        if (expireSecond != NO_EXPIRE) {
            setArgs = setArgs.ex(expireSecond);
        }
        return syncSet(redisClient, key, value, setArgs);
    }

    public static boolean syncSet(RedisClient redisClient, String key, String value, long expireSecond) {
        SetArgs setArgs = null;
        if (expireSecond != NO_EXPIRE) {
            setArgs = SetArgs.Builder.ex(expireSecond);
        }
        return syncSet(redisClient, key, value, setArgs);
    }

    private static boolean syncSet(RedisClient redisClient, String key, String value, SetArgs setArgs) {
        try (StatefulRedisConnection<String, String> redisConnection = redisClient.connect()) {
            String result;
            if (setArgs == null) {
                result = redisConnection.sync().set(key, value);
            } else {
                result = redisConnection.sync().set(key, value, setArgs);
            }
            return SET_OK.equals(result);
        }
    }

    public static long delete(RedisClient redisClient, String... keys) {
        try (StatefulRedisConnection<String, String> redisConnection = redisClient.connect()) {
            return redisConnection.sync().del(keys);
        }
    }

    public static StatefulRedisConnection<String, String> getRedisConnection(RedisClient redisClient) {
        return redisClient.connect();
    }
}
