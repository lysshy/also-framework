package com.also.admin.mapper.pg;

import com.also.admin.entity.pg.RewardPg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RewardPgMapper extends BaseMapper<RewardPg> {
}
