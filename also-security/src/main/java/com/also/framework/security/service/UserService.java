package com.also.framework.security.service;

import com.also.framework.security.domain.auth.BaseRole;
import com.also.framework.security.domain.auth.UserDetail;

public interface UserService {
    UserDetail findByUsername(String username);

    BaseRole findRoleByUserId(long userId);
}
