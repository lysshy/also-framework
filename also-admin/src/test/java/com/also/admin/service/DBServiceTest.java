package com.also.admin.service;

import com.also.admin.entity.oracle.ReportOracle;
import com.also.admin.entity.pg.ReportPg;
import com.also.admin.service.oracle.ReportOracleService;
import com.also.admin.service.pg.ReportPgService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DBServiceTest {

    @Autowired
    private ReportOracleService reportOracleService;

    @Autowired
    private ReportPgService reportPgService;

    @Test
    public void test() {
        List<ReportOracle> reportOracles = reportOracleService.selectReports(new QueryWrapper<>());
        System.out.println(reportOracles.size());

        System.out.println(reportOracleService.selectPage(1, 1).size());

        List<ReportPg> reportPgs = reportPgService.selectReports(new QueryWrapper<>(), 1000);

        System.out.println(reportPgs.size());
        System.out.println(reportPgService.selectPage(1, 1).size());
    }
}
