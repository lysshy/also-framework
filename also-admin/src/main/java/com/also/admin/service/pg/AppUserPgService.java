package com.also.admin.service.pg;

import com.also.admin.entity.pg.AppUserPg;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

public interface AppUserPgService {

    List<AppUserPg> selectAppUsers(QueryWrapper<AppUserPg> queryWrapper, int limitSize);

    void batchInsert(List<AppUserPg> appUserPgs);

    void batchUpdate(List<AppUserPg> appUserPgs);

}
