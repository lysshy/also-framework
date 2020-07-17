package com.also.admin.service.pg.impl;

import com.also.admin.constants.DataSourceConstant;
import com.also.admin.entity.pg.AppUserPg;
import com.also.admin.mapper.pg.AppUserPgMapper;
import com.also.admin.service.pg.AppUserPgService;
import com.also.framework.datasource.annotation.DataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSource(DataSourceConstant.PG)
public class AppUserPgServiceImpl extends ServiceImpl<AppUserPgMapper, AppUserPg> implements AppUserPgService {
    @Override
    public List<AppUserPg> selectAppUsers(QueryWrapper<AppUserPg> queryWrapper, int limitSize) {
        queryWrapper.last(" limit " + limitSize);
        return this.list(queryWrapper);
    }

    @Override
    public void batchInsert(List<AppUserPg> appUserPgs) {
        this.saveBatch(appUserPgs);
    }

    @Override
    public void batchUpdate(List<AppUserPg> appUserPgs) {
        this.updateBatchById(appUserPgs);
    }
}
