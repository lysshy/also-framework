package com.also.admin.service.pg;

import com.also.admin.entity.pg.RewardPg;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

public interface RewardPgService {

    List<RewardPg> selectRewards(QueryWrapper<RewardPg> queryWrapper, int limitSize);

    void batchUpdate(List<RewardPg> rewardPgs);

    void batchInsert(List<RewardPg> rewardPgs);
}
