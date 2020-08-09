package com.also.framework.security.common.config;

import com.also.framework.common.service.MessageService;
import com.also.framework.security.common.config.property.IgnoreUrlProperties;
import com.also.framework.security.common.config.property.ValidateCodeProperties;
import com.also.framework.security.common.handler.AuthorityService;
import com.also.framework.security.common.handler.DefaultAccessDeniedHandler;
import com.also.framework.security.common.handler.DefaultAuthenticationFailureHandler;
import com.also.framework.security.common.handler.DefaultAuthorityService;
import com.also.framework.security.common.service.BasePermissionService;
import com.also.framework.security.common.service.BaseRoleService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.annotation.PostConstruct;

@Configuration
public class AlsoSecurityCommonConfig {

    private final MessageSource messageSource;

    public AlsoSecurityCommonConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @PostConstruct
    public void init() {
        if (messageSource instanceof ResourceBundleMessageSource) {
            ResourceBundleMessageSource resourceBundleMessageSource = (ResourceBundleMessageSource) messageSource;
            resourceBundleMessageSource.addBasenames("messages/messages-also-security-common");
        }
    }

    @Bean
    @ConfigurationProperties(prefix = "also.security.validate")
    public ValidateCodeProperties validateCodeProperties() {
        return new ValidateCodeProperties();
    }

    @Bean
    @ConfigurationProperties(prefix = "also.security.ignore")
    public IgnoreUrlProperties ignoreUrlProperties() {
        return new IgnoreUrlProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthorityService authorityService(BaseRoleService baseRoleService, BasePermissionService basePermissionService) {
        return new DefaultAuthorityService(baseRoleService, basePermissionService);
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationFailureHandler.class)
    public AuthenticationFailureHandler authenticationFailureHandler(MessageService messageService) {
        return new DefaultAuthenticationFailureHandler(messageService);
    }

    @Bean
    @ConditionalOnMissingBean
    public AccessDeniedHandler accessDeniedHandler(MessageService messageService) {
        return new DefaultAccessDeniedHandler(messageService);
    }

}

