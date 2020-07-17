package com.also.admin.service.oracle;


import com.also.admin.entity.oracle.AppUserOracle;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

public interface AppUserOracleService {

    List<AppUserOracle> selectAppUsers(QueryWrapper<AppUserOracle> queryWrapper);

    void batchDelete(List<AppUserOracle> appUserOracles);
}
