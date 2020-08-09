package com.also.framework.security.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author JoeTao
 * createAt: 2018/9/17
 */
@Data
@AllArgsConstructor
public class ResponseUserToken {
    private String token;
    private UserDetail userDetail;
}
