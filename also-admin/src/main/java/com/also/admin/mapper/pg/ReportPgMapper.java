package com.also.admin.mapper.pg;

import com.also.admin.entity.pg.ReportPg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface ReportPgMapper extends BaseMapper<ReportPg> {
}
