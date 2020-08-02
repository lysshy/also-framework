package com.also.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.also.framework", "com.also.admin"})
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.also.admin", "com.also.framework"})
//@EnablePrometheusEndpoint
//@EnableSpringBootMetricsCollector
public class AdminServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerStarter.class, args);
    }

}
