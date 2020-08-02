package com.also.framework.security.common.service.impl;


import com.also.framework.security.common.domain.auth.BasePermission;
import com.also.framework.security.common.mapper.BasePermissionMapper;
import com.also.framework.security.common.service.BasePermissionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasePermissionServiceImpl extends ServiceImpl<BasePermissionMapper, BasePermission> implements BasePermissionService {

    @Autowired
    private BasePermissionMapper basePermissionMapper;

    @Override
    public List<BasePermission> selectByRoleIds(List<Long> roleIds) {
        QueryWrapper<BasePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("rp.role_id", roleIds);
        return basePermissionMapper.selectByRoleIds(queryWrapper);
    }

    @Override
    public BasePermission select(String url, String methodType) {
        QueryWrapper<BasePermission> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BasePermission::getUrl, url);
        queryWrapper.lambda().eq(BasePermission::getMethodType, methodType);
        return this.getOne(queryWrapper);
    }

}
