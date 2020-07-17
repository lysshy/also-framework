package com.also.admin.service.pg.impl;

import com.also.admin.constants.DataSourceConstant;
import com.also.admin.entity.pg.RewardPg;
import com.also.admin.mapper.pg.RewardPgMapper;
import com.also.admin.service.pg.RewardPgService;
import com.also.framework.datasource.annotation.DataSource;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSource(DataSourceConstant.PG)
public class RewardPgServiceImpl extends ServiceImpl<RewardPgMapper, RewardPg> implements RewardPgService {
    @Override
    public List<RewardPg> selectRewards(QueryWrapper<RewardPg> queryWrapper, int limitSize) {
        queryWrapper.last(" limit " + limitSize);
        return this.list(queryWrapper);
    }

    @Override
    public void batchUpdate(List<RewardPg> rewardPgs) {
        this.updateBatchById(rewardPgs);
    }

    @Override
    public void batchInsert(List<RewardPg> rewardPgs) {
        this.saveBatch(rewardPgs);
    }
}
