package com.also.framework.security.common.service.impl;


import com.also.framework.security.common.domain.auth.BaseUserRole;
import com.also.framework.security.common.mapper.BaseUserRoleMapper;
import com.also.framework.security.common.service.BaseUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseUserRoleServiceImpl extends ServiceImpl<BaseUserRoleMapper, BaseUserRole> implements BaseUserRoleService {
    @Override
    public void insert(BaseUserRole baseUserRole) {
        this.save(baseUserRole);
    }
}
