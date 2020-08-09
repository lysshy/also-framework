package com.also.framework.security.jwt.controller;

import com.also.framework.security.common.domain.BaseResult;
import com.also.framework.security.common.domain.req.LoginReq;
import com.also.framework.security.common.domain.resp.LoginResp;
import com.also.framework.security.jwt.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("login")
    public BaseResult login(@RequestBody @Validated LoginReq loginReq) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        String jwt = jwtUtil.createJWT(authentication, loginReq.isRememberMe());
        LoginResp loginResp = new LoginResp();
        loginResp.setToken(jwt);
        loginResp.setUsername(loginReq.getUsername());
        return BaseResult.ok(loginResp);
    }

    @PostMapping("loginForm")
    public BaseResult loginForm(@Validated LoginReq loginReq) {
        return login(loginReq);
    }
}
