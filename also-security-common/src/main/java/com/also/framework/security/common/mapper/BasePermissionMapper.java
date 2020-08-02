package com.also.framework.security.common.mapper;

import com.also.framework.security.common.domain.auth.BasePermission;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BasePermissionMapper extends BaseMapper<BasePermission> {

    @Select("select p.* from sys_role_permission rp left join sys_permission p on p.id=rp.permission_id ${ew.customSqlSegment}")
    List<BasePermission> selectByRoleIds(@Param("ew") QueryWrapper<BasePermission> queryWrapper);

}
