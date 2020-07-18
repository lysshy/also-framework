package com.also.admin.service.oracle;

import com.also.admin.entity.oracle.ReportOracle;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

public interface ReportOracleService {

    void batchInsert(List<ReportOracle> reports);

    List<ReportOracle> selectReports(QueryWrapper<ReportOracle> queryWrapper);

    void batchDelete(List<ReportOracle> reportOracles);

    List<ReportOracle> selectPage(int page, int pageSize);
}
