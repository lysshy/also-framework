package com.also.framework.datasource.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataSourceProperties {

    private boolean defaultDatabase = false;
    private String name;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
