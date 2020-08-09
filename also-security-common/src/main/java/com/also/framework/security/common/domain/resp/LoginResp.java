package com.also.framework.security.common.domain.resp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResp {

    private String username;

    private String token;
}
