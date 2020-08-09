package com.also.framework.cache;

import com.also.framework.cache.redis.RedisHelper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("redis")
public class RedisCacheTest {

    @Autowired
    private RedisHelper redisHelper;

    @Test
    public void testSet() throws Exception {
        redisHelper.syncSet("test", "test1");
        Assert.assertEquals("test1", redisHelper.syncGet("test"));
        Assert.assertFalse(redisHelper.syncSetNX("test", "lysshy123"));
        redisHelper.delete("test");
        Assert.assertNull(redisHelper.syncGet("test"));
    }

    @Test
    public void testExpireTime() throws Exception {
        redisHelper.syncSet("test", "test1", 2);
        Assert.assertEquals("test1", redisHelper.syncGet("test"));
        Thread.sleep(3000);
        Assert.assertNull(redisHelper.syncGet("test"));
    }

    @Test
    public void testMulti() throws Exception {
        RedisClient redisClient = redisHelper.getClient();
        redisHelper.delete("test1", "test");
        try (StatefulRedisConnection<String, String> redisConnection = redisClient.connect()) {
            RedisCommands<String, String> redisCommands = redisConnection.sync();
            redisCommands.multi();
            Assert.assertNull(redisCommands.set("test", "test1"));
            Assert.assertNull(redisCommands.set("test1", "test2"));
            Assert.assertNull(redisHelper.syncGet("test"));

            redisCommands.exec();
        }

        redisHelper.returnClient(redisClient);
        Assert.assertEquals("test1", redisHelper.syncGet("test"));
        Assert.assertEquals("test2", redisHelper.syncGet("test1"));

    }
}
