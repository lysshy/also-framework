package com.also.admin.mapper.oracle;

import com.also.admin.entity.oracle.ReportOracle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ReportOracleMapper extends BaseMapper<ReportOracle> {

    void insertBatch(List<ReportOracle> reportOracleList);
}
