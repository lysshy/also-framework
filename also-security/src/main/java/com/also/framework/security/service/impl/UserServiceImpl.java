package com.also.framework.security.service.impl;

import com.also.framework.security.domain.auth.BaseRole;
import com.also.framework.security.domain.auth.UserDetail;
import com.also.framework.security.mapper.AuthMapper;
import com.also.framework.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final AuthMapper authMapper;

    @Autowired
    public UserServiceImpl(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    @Override
    public UserDetail findByUsername(String username) {
        return authMapper.findByUsername(username);
    }

    @Override
    public BaseRole findRoleByUserId(long userId) {
        List<BaseRole> roles = authMapper.findRoleByUserId(userId);
        if (!CollectionUtils.isEmpty(roles)) {
            return roles.get(0);
        }
        return null;
    }
}
