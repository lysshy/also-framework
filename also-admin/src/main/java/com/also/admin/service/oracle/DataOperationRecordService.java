package com.also.admin.service.oracle;

import com.also.admin.entity.oracle.DataOperationRecord;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public interface DataOperationRecordService {

    DataOperationRecord select(String tableName, String operationType, String operationSide);

    DataOperationRecord select(QueryWrapper<DataOperationRecord> queryWrapper);

    void updateDataOperationRecord(String tableName, String operationType, String operationSide, String condition);

    void updateDataOperationRecord(DataOperationRecord update, QueryWrapper<DataOperationRecord> queryWrapper);
}
