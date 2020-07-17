package com.also.admin.mapper.oracle;

import com.also.admin.entity.oracle.DataOperationRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface DataOperationRecordMapper extends BaseMapper<DataOperationRecord> {
}
