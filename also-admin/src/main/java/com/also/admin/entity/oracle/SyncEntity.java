package com.also.admin.entity.oracle;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SyncEntity {

    private String operationSide;

    private String operationType;

    private Date syncTime;

    private Integer sourceId;
}
