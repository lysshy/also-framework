package com.also.framework.security.session.config.properties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidateCodeProperties {

    private int length = 6;

    private int timeout = 60;
}
