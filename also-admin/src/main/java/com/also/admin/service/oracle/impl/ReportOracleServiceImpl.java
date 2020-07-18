package com.also.admin.service.oracle.impl;

import com.also.admin.constants.DataSourceConstant;
import com.also.admin.entity.oracle.ReportOracle;
import com.also.admin.mapper.oracle.ReportOracleMapper;
import com.also.admin.service.oracle.ReportOracleService;
import com.also.framework.datasource.annotation.DataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@DataSource(DataSourceConstant.ORACLE)
@Slf4j
public class ReportOracleServiceImpl extends ServiceImpl<ReportOracleMapper, ReportOracle> implements ReportOracleService {

    @Autowired
    private ReportOracleMapper reportOracleMapper;

    @Override
    public void batchInsert(List<ReportOracle> reports) {
        reportOracleMapper.insertBatch(reports);
    }

    @Override
    public List<ReportOracle> selectReports(QueryWrapper<ReportOracle> queryWrapper) {
        return list(queryWrapper);
    }

    @Override
    public void batchDelete(List<ReportOracle> reportOracles) {
        List<String> ids = reportOracles.stream().map(ReportOracle::getId).collect(Collectors.toList());
        removeByIds(ids);
    }

    @Override
    public List<ReportOracle> selectPage(int page, int pageSize) {
        Page<ReportOracle> p = new Page<>(page, pageSize);
        return this.page(p).getRecords();
    }
}
