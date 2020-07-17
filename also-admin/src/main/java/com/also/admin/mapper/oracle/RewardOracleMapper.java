package com.also.admin.mapper.oracle;

import com.also.admin.entity.oracle.RewardOracle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface RewardOracleMapper extends BaseMapper<RewardOracle> {

    void insertBatch(List<RewardOracle> rewardOracles);
}
