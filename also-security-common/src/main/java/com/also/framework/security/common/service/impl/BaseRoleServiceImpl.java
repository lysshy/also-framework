package com.also.framework.security.common.service.impl;


import com.also.framework.security.common.domain.auth.BaseRole;
import com.also.framework.security.common.mapper.BaseRoleMapper;
import com.also.framework.security.common.service.BaseRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseRoleServiceImpl extends ServiceImpl<BaseRoleMapper, BaseRole> implements BaseRoleService {

    private final BaseRoleMapper baseRoleMapper;

    @Autowired
    public BaseRoleServiceImpl(BaseRoleMapper baseRoleMapper) {
        this.baseRoleMapper = baseRoleMapper;
    }

    @Override
    public BaseRole selectById(long id) {
        return this.getById(id);
    }

    @Override
    public List<BaseRole> selectByUserId(long id) {
        return baseRoleMapper.selectRoleByUserId(id);
    }

    @Override
    public void insert(BaseRole baseRole) {
        this.save(baseRole);
    }
}
