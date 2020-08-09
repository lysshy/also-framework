package com.also.framework.cache.config;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class CacheCondition extends SpringBootCondition {

    private static final Map<String, Class<?>> CACHE_MAP = new HashMap<>();

    static {
        CACHE_MAP.put("redis", RedisCacheConfig.class);
        CACHE_MAP.put("caffeine", CaffeineCacheConfig.class);
    }

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        String cacheType = env.getProperty("also.cache.type");
        if (StringUtils.isEmpty(cacheType)) {
            return ConditionOutcome.noMatch("also.cache.type is empty");
        }

        Class<?> configClass = CACHE_MAP.get(cacheType);
        if (configClass == null) {
            return ConditionOutcome.noMatch("unsupported cache type: " + cacheType);
        }

        String sourceClass = "";
        if (metadata instanceof ClassMetadata) {
            sourceClass = ((ClassMetadata) metadata).getClassName();
        }

        if (configClass.getName().equals(sourceClass)) {
            return ConditionOutcome.match("matched cache type: " + cacheType);
        }
        return ConditionOutcome.noMatch("no match cache type: " + cacheType);
    }
}
