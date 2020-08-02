package com.also.framework.security.common.service;


import com.also.framework.security.common.domain.auth.BaseUser;

public interface BaseUserService {

    BaseUser selectById(long id);

    void insertUser(BaseUser user);

    BaseUser findByUsername(String username);
}
