package com.also.framework.security.service.impl;

import com.also.framework.security.domain.auth.BaseUser;
import com.also.framework.security.mapper.BaseUserMapper;
import com.also.framework.security.service.BaseUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements BaseUserService {
    @Override
    public BaseUser selectById(long id) {
        return this.getById(id);
    }

    @Override
    public void insertUser(BaseUser user) {
        this.save(user);
    }

    @Override
    public BaseUser findByUsername(String username) {
        QueryWrapper<BaseUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BaseUser::getUsername, username);
        return this.getOne(queryWrapper);
    }
}
