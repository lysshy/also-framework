package com.also.framework.datasource.enums;

import lombok.Getter;

@Getter
public enum DataSourceTypeEnum {
    DRUID("druid"),
    HIKARI("hikari");

    private String name;

    DataSourceTypeEnum(String name) {
        this.name = name;
    }
}
