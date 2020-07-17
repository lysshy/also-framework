package com.also.admin.service.pg.impl;

import com.also.admin.constants.DataSourceConstant;
import com.also.admin.entity.pg.ReportPg;
import com.also.admin.mapper.pg.ReportPgMapper;
import com.also.admin.service.pg.ReportPgService;
import com.also.framework.datasource.annotation.DataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSource(DataSourceConstant.PG)
public class ReportPgServiceImpl extends ServiceImpl<ReportPgMapper, ReportPg> implements ReportPgService {

    @Override
    public List<ReportPg> selectReports(QueryWrapper<ReportPg> queryWrapper, int limitSize) {
        queryWrapper.last(" limit " + limitSize);
        return this.list(queryWrapper);
    }

    @Override
    public void batchUpdate(List<ReportPg> reportPgs) {
        this.updateBatchById(reportPgs);
    }

    @Override
    public void batchInsert(List<ReportPg> reportPgs) {
        this.saveBatch(reportPgs);
    }
}
