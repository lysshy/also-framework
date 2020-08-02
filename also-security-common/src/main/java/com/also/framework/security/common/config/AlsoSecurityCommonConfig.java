package com.also.framework.security.common.config;

import com.also.framework.common.service.MessageService;
import com.also.framework.security.common.config.property.ValidateCodeProperties;
import com.also.framework.security.common.handler.AuthorityService;
import com.also.framework.security.common.handler.DefaultAuthenticationFailureHandler;
import com.also.framework.security.common.handler.DefaultAuthorityService;
import com.also.framework.security.common.service.BasePermissionService;
import com.also.framework.security.common.service.BaseRoleService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class AlsoSecurityCommonConfig {

    @Bean
    @ConfigurationProperties(prefix = "also.security.validate")
    public ValidateCodeProperties validateCodeProperties() {
        return new ValidateCodeProperties();
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

}

