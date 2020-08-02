package com.also.admin.config;

import com.also.framework.common.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.annotation.PostConstruct;

@Configuration
public class AdminConfig {

    @Autowired
    private ResourceBundleMessageSource resourceBundleMessageSource;

    @PostConstruct
    public void init() {
        resourceBundleMessageSource.addBasenames("messages/messages-admin");
    }
//
//    @Bean
//    public InvalidSessionStrategy invalidSessionStrategy(MessageService messageService) {
//        return new MyInvalidSessionStrategy(messageService);
//    }


}
