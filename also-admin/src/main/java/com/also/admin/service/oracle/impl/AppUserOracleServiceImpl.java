package com.also.admin.service.oracle.impl;

import com.also.admin.constants.DataSourceConstant;
import com.also.admin.entity.oracle.AppUserOracle;
import com.also.admin.mapper.oracle.AppUserOracleMapper;
import com.also.admin.service.oracle.AppUserOracleService;
import com.also.framework.datasource.annotation.DataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@DataSource(DataSourceConstant.ORACLE)
public class AppUserOracleServiceImpl extends ServiceImpl<AppUserOracleMapper, AppUserOracle> implements AppUserOracleService {

    @Override
    public List<AppUserOracle> selectAppUsers(QueryWrapper<AppUserOracle> queryWrapper) {
        return this.list(queryWrapper);
    }

    @Override
    public void batchDelete(List<AppUserOracle> appUserOracles) {
        List<String> ids = appUserOracles.stream().map(AppUserOracle::getId).collect(Collectors.toList());
        removeByIds(ids);
    }
}
