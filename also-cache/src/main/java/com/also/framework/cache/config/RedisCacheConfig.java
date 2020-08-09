package com.also.framework.cache.config;

import com.also.framework.cache.redis.RedisClientPool;
import com.also.framework.cache.redis.RedisClientPoolConfig;
import com.also.framework.cache.redis.RedisClientPoolFactory;
import com.also.framework.cache.redis.RedisHelper;
import com.also.framework.cache.redis.RedisPoolProperties;
import com.also.framework.cache.redis.RedisProperties;
import io.lettuce.core.RedisURI;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(RedisURI.class)
@Conditional(CacheCondition.class)
public class RedisCacheConfig {

    @Bean
    public ClientResources clientResources() {
        return DefaultClientResources.create();
    }

    @Bean
    @ConfigurationProperties(prefix = "also.cache.redis")
    public RedisProperties redisProperties() {
        return new RedisProperties();
    }

    @Bean
    @ConditionalOnBean(RedisProperties.class)
    public RedisURI redisURI(RedisProperties redisProperties) {
        return RedisURI.builder().withHost(redisProperties.getHost()).withPort(redisProperties.getPort())
                .withPassword(redisProperties.getPassword()).build();
    }

    @Bean
    @ConfigurationProperties(prefix = "also.cache.pool")
    public RedisPoolProperties redisPoolProperties() {
        return new RedisPoolProperties();
    }

    @Bean
    public RedisClientPool redisClientPool(ClientResources clientResources, RedisURI redisURI, RedisPoolProperties redisPoolProperties) {
        RedisClientPoolFactory redisClientPoolFactory = new RedisClientPoolFactory(clientResources, redisURI);
        RedisClientPoolConfig redisClientPoolConfig = new RedisClientPoolConfig(redisPoolProperties);
        return new RedisClientPool(redisClientPoolFactory, redisClientPoolConfig);
    }

    @Bean
    @ConditionalOnBean(RedisClientPool.class)
    public RedisHelper redisHelper(RedisClientPool redisClientPool) {
        return new RedisHelper(redisClientPool);
    }

}
