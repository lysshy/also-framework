package com.also.framework.security.session.config;

import com.also.framework.common.service.MessageService;
import com.also.framework.security.common.config.property.AlsoSecurityProperties;
import com.also.framework.security.session.config.handler.DefaultInvalidSessionStrategy;
import com.also.framework.security.session.config.handler.DefaultSessionExpiredStrategy;
import com.also.framework.security.session.config.handler.SessionAuthenticationSuccessHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.annotation.PostConstruct;

@Configuration
public class AlsoSessionConfig {

    private final MessageSource messageSource;

    public AlsoSessionConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @PostConstruct
    public void init() {
        if (messageSource instanceof ResourceBundleMessageSource) {
            ResourceBundleMessageSource resourceBundleMessageSource = (ResourceBundleMessageSource) messageSource;
            resourceBundleMessageSource.addBasenames("messages/messages-also-session");
        }
    }

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy(MessageService messageService) {
        return new DefaultInvalidSessionStrategy(messageService);
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(MessageService messageService) {
        return new DefaultSessionExpiredStrategy(messageService);
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationSuccessHandler.class)
    public AuthenticationSuccessHandler authenticationSuccessHandler(AlsoSecurityProperties properties) {
        return new SessionAuthenticationSuccessHandler(properties);
    }

}
