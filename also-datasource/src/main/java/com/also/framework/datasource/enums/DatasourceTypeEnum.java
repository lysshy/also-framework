package com.also.framework.datasource.enums;

import lombok.Getter;

@Getter
public enum DatasourceTypeEnum {
    DRUID("druid"),
    HIKARI("hikari");

    private String name;

    DatasourceTypeEnum(String name) {
        this.name = name;
    }
}
