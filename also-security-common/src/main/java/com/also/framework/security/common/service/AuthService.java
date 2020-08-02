package com.also.framework.security.common.service;


import com.also.framework.security.common.domain.UserDetail;
import com.also.framework.security.common.domain.auth.BaseUser;

public interface AuthService {

    /**
     * 注册用户
     * @return
     */
    UserDetail register(BaseUser baseUser);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    UserDetail login(String username, String password);

    /**
     * 登出
     * @param token
     */
    void logout(String token);

}
