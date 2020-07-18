package com.also.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.also.framework", "com.also.admin"})
public class AdminServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerStarter.class, args);
    }
}
