package com.also.framework.datasource.config.properties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StatFilterProperties {

    private boolean enabled = true;
    private String urlPattern = "/*";
    private String exclusions = ".js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*";
}
