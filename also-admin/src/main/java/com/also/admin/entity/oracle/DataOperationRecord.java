package com.also.admin.entity.oracle;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@TableName("t_data_operation_record")
@Setter
@Getter
public class DataOperationRecord {

    @TableId
    private Integer id;

    private String operationType;

    private String operationSide;

    private String tableName;

    private Date updateTime;

    private String condition;

}
