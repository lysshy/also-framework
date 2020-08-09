package com.also.framework.security.service;

import com.also.framework.security.domain.auth.BaseUser;
import com.also.framework.security.domain.auth.ResponseUserToken;
import com.also.framework.security.domain.auth.UserDetail;

public interface AuthService {

    /**
     * 注册用户
     * @param userDetail
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

    /**
     * 刷新Token
     * @param oldToken
     * @return
     */
    ResponseUserToken refresh(String oldToken);

    /**
     * 根据Token获取用户信息
     * @param token
     * @return
     */
    UserDetail getUserByToken(String token);
}
