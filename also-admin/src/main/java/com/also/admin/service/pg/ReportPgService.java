package com.also.admin.service.pg;

import com.also.admin.entity.pg.ReportPg;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

public interface ReportPgService {

    List<ReportPg> selectReports(QueryWrapper<ReportPg> queryWrapper, int limitSize);

    void batchUpdate(List<ReportPg> reportPgs);

    void batchInsert(List<ReportPg> reportPgs);

    List<ReportPg> selectPage(int pageNo, int pageSize);
}
