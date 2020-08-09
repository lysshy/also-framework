package com.also.framework.actuator.server;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class ActuatorServerStarter {

    public static void main(String[] args) {
        SpringApplication.run(ActuatorServerStarter.class, args);
    }
}
