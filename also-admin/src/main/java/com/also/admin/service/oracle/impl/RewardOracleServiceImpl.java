package com.also.admin.service.oracle.impl;

import com.also.admin.constants.DataSourceConstant;
import com.also.admin.entity.oracle.RewardOracle;
import com.also.admin.mapper.oracle.RewardOracleMapper;
import com.also.admin.service.oracle.RewardOracleService;
import com.also.framework.datasource.annotation.DataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@DataSource(DataSourceConstant.ORACLE)
public class RewardOracleServiceImpl extends ServiceImpl<RewardOracleMapper, RewardOracle> implements RewardOracleService {

    @Autowired
    private RewardOracleMapper rewardOracleMapper;

    @Override
    public void batchUpdate(List<RewardOracle> rewardOracles) {
        this.updateBatchById(rewardOracles);
    }

    @Override
    public List<RewardOracle> selectRewards(QueryWrapper<RewardOracle> queryWrapper) {
        return list(queryWrapper);
    }

    @Override
    public void batchDelete(List<RewardOracle> rewardOracles) {
        List<String> ids = rewardOracles.stream().map(RewardOracle::getId).collect(Collectors.toList());
        removeByIds(ids);
    }

    @Override
    public void batchInsert(List<RewardOracle> rewardOracles) {

    }
}
