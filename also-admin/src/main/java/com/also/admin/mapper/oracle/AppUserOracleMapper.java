package com.also.admin.mapper.oracle;

import com.also.admin.entity.oracle.AppUserOracle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AppUserOracleMapper extends BaseMapper<AppUserOracle> {

    void insertBatch(List<AppUserOracle> appUserOracles);
}
