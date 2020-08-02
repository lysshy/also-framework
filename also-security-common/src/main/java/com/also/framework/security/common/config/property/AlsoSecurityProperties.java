package com.also.framework.security.common.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "also.security", ignoreInvalidFields = true)
@Component
@Setter
@Getter
public class AlsoSecurityProperties {

    private String loginRequestUrl = "/login";

    private String loginRequestType = "post";

    private String loginSuccessUrl = "/loginSuccess";

}
