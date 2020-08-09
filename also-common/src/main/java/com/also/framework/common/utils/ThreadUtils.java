package com.also.framework.common.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadUtils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            log.error("sleep error.", e);
        }
    }
}
