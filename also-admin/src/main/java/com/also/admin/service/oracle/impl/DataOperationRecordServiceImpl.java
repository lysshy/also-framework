package com.also.admin.service.oracle.impl;


import com.also.admin.constants.DataSourceConstant;
import com.also.admin.entity.oracle.DataOperationRecord;
import com.also.admin.mapper.oracle.DataOperationRecordMapper;
import com.also.admin.service.oracle.DataOperationRecordService;
import com.also.framework.datasource.annotation.DataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@DataSource(DataSourceConstant.ORACLE)
public class DataOperationRecordServiceImpl extends ServiceImpl<DataOperationRecordMapper, DataOperationRecord> implements DataOperationRecordService {

    @Override
    public DataOperationRecord select(String tableName, String operationType, String operationSide) {
        QueryWrapper<DataOperationRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DataOperationRecord::getTableName, tableName)
                .eq(DataOperationRecord::getOperationType, operationType).eq(DataOperationRecord::getOperationSide, operationSide);
        return getOne(queryWrapper);
    }

    @Override
    public DataOperationRecord select(QueryWrapper<DataOperationRecord> queryWrapper) {
        return getOne(queryWrapper);
    }

    @Override
    public void updateDataOperationRecord(String tableName, String operationType, String operationSide, String condition) {
        DataOperationRecord update = new DataOperationRecord();
        update.setCondition(condition);
        update.setUpdateTime(new Date());

        QueryWrapper<DataOperationRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DataOperationRecord::getTableName, tableName)
                .eq(DataOperationRecord::getOperationType, operationType).eq(DataOperationRecord::getOperationSide, operationSide);

        this.update(update, queryWrapper);
    }

    @Override
    public void updateDataOperationRecord(DataOperationRecord update, QueryWrapper<DataOperationRecord> queryWrapper) {
        this.update(update, queryWrapper);
    }
}
