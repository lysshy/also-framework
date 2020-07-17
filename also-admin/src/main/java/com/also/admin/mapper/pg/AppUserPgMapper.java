package com.also.admin.mapper.pg;

import com.also.admin.entity.pg.AppUserPg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface AppUserPgMapper extends BaseMapper<AppUserPg> {
}
