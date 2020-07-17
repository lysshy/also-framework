package com.also.admin.service.oracle;

import com.also.admin.entity.oracle.RewardOracle;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

public interface RewardOracleService {

    void batchUpdate(List<RewardOracle> rewardOracles);

    List<RewardOracle> selectRewards(QueryWrapper<RewardOracle> queryWrapper);

    void batchDelete(List<RewardOracle> rewardOracles);

    void batchInsert(List<RewardOracle> rewardOracles);
}
