package com.also.framework.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("caffeine")
public class CaffeineCacheTest {

    @Autowired
    CacheManager cacheManager;

    @Test
    public void testSet() throws InterruptedException {
        Cache userCache = cacheManager.getCache("user");
        Assert.assertNotNull(userCache);
        userCache.put("test", "test1");
        Assert.assertEquals("test1", userCache.get("test", String.class));

        Thread.sleep(3000);
        Assert.assertNull(userCache.get("test", String.class));

        Cache roleCache = cacheManager.getCache("role");
        Assert.assertNotNull(roleCache);
        roleCache.put("test", "test1");
        Assert.assertEquals("test1", roleCache.get("test", String.class));

        Thread.sleep(3000);
        Assert.assertEquals("test1", roleCache.get("test", String.class));
    }
}
