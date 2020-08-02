package com.also.framework.datasource.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.also.framework.*.mapper")
public class AlsoMyBatisPlusConfig {
}
