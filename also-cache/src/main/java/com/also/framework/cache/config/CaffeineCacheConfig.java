package com.also.framework.cache.config;

import com.also.framework.cache.caffeine.CaffeineProperties;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConditionalOnClass({ Caffeine.class, CaffeineCacheManager.class })
@Conditional(CacheCondition.class)
public class CaffeineCacheConfig {

    @Bean
    @ConfigurationProperties(prefix = "also.cache.caffeine")
    public CaffeineProperties caffeineProperties() {
        return new CaffeineProperties();
    }

    @Bean
    public CacheManager cacheManager(CaffeineProperties caffeineProperties) {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        if (!CollectionUtils.isEmpty(caffeineProperties.getCacheItems())) {
            for (CaffeineProperties.CaffeineItem caffeineItem : caffeineProperties.getCacheItems()) {
                String spec = caffeineItem.getSpec();
                if (StringUtils.isEmpty(spec)) {
                    //没有单独设置，就设置为全局的
                    spec = caffeineProperties.getSpec();
                }
                caches.add(buildCache(caffeineItem.getName(), spec));
            }
        }
        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }

    public Cache buildCache(String name, String spec) {
        if (spec != null) {
            return new CaffeineCache(name, Caffeine.from(spec).build());
        }
        return new CaffeineCache(name, Caffeine.newBuilder().build());
    }

}
