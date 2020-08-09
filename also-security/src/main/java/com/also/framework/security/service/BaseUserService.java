package com.also.framework.security.service;

import com.also.framework.security.domain.auth.BaseUser;

public interface BaseUserService {

    BaseUser selectById(long id);

    void insertUser(BaseUser user);

    BaseUser findByUsername(String username);
}
