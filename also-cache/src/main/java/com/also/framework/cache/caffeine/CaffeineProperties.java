package com.also.framework.cache.caffeine;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CaffeineProperties {

    private List<CaffeineItem> cacheItems;

    private String spec;

    @Getter
    @Setter
    public static class CaffeineItem {
        private String name;

        private String spec;
    }
}
