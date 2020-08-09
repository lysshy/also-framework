package com.also.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.also.admin", "com.also.framework"})
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = {"com.also.admin", "com.also.framework"})
//@EnablePrometheusEndpoint
//@EnableSpringBootMetricsCollector
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class AdminServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerStarter.class, args);
    }

}
