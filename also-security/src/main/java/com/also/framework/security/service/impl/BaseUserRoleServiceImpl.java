package com.also.framework.security.service.impl;

import com.also.framework.security.domain.auth.BaseUserRole;
import com.also.framework.security.mapper.BaseUserRoleMapper;
import com.also.framework.security.service.BaseUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseUserRoleServiceImpl extends ServiceImpl<BaseUserRoleMapper, BaseUserRole> implements BaseUserRoleService {
    @Override
    public void insert(BaseUserRole baseUserRole) {
        this.save(baseUserRole);
    }
}
