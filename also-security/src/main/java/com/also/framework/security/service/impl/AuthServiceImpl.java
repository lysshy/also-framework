package com.also.framework.security.service.impl;

import com.also.framework.security.domain.ResultCode;
import com.also.framework.security.domain.ResultJson;
import com.also.framework.security.domain.auth.BaseUser;
import com.also.framework.security.domain.auth.BaseUserRole;
import com.also.framework.security.domain.auth.ResponseUserToken;
import com.also.framework.security.domain.auth.BaseRole;
import com.also.framework.security.domain.auth.UserDetail;
import com.also.framework.security.exception.CustomException;
import com.also.framework.security.mapper.AuthMapper;
import com.also.framework.security.service.AuthService;
import com.also.framework.security.service.BaseRoleService;
import com.also.framework.security.service.BaseUserRoleService;
import com.also.framework.security.service.BaseUserService;
import com.also.framework.security.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    private BaseRoleService baseRoleService;
    private BaseUserService baseUserService;
    private BaseUserRoleService baseUserRoleService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager
            , @Qualifier("CustomUserDetailsService") UserDetailsService userDetailsService
            , BaseRoleService baseRoleService
            , BaseUserService baseUserService
            , BaseUserRoleService baseUserRoleService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.baseRoleService = baseRoleService;
        this.baseUserService = baseUserService;
        this.baseUserRoleService = baseUserRoleService;
    }

    @Override
    public UserDetail register(BaseUser baseUser) {
        final String username = baseUser.getUsername();
        if(baseUserService.findByUsername(username)!=null) {
            throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户已存在"));
        }
        UserDetail userDetail = new UserDetail();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String encodePwd = encoder.encode(baseUser.getPassword());
        userDetail.setPassword(encodePwd);
        userDetail.setUsername(baseUser.getUsername());

        baseUser.setPassword(encodePwd);
        baseUserService.insertUser(baseUser);
        long roleId = 1L;
        BaseRole role = baseRoleService.selectById(roleId);
        userDetail.setRoles(Arrays.asList(role));
        BaseUserRole baseUserRole = new BaseUserRole();
        baseUserRole.setRoleId(role.getId());
        baseUserRole.setUserId(baseUser.getId());
        baseUserRoleService.insert(baseUserRole);
        return userDetail;
    }

    @Override
    public UserDetail login(String username, String password) {
        //用户验证
        final Authentication authentication = authenticate(username, password);
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
//        final String token = jwtTokenUtil.generateAccessToken(userDetail);
//        //存储token
//        jwtTokenUtil.putToken(username, token);
//        return new ResponseUserToken(token, userDetail);
        return userDetail;
    }

    @Override
    public void logout(String token) {
//        token = token.substring(tokenHead.length());
//        String userName = jwtTokenUtil.getUsernameFromToken(token);
//        jwtTokenUtil.deleteToken(userName);
    }

    @Override
    public ResponseUserToken refresh(String oldToken) {
//        String token = oldToken.substring(tokenHead.length());
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        UserDetail userDetail = (UserDetail) userDetailsService.loadUserByUsername(username);
//        if (jwtTokenUtil.canTokenBeRefreshed(token, userDetail.getLastPasswordResetDate())){
//            token =  jwtTokenUtil.refreshToken(token);
//            return new ResponseUserToken(token, userDetail);
//        }
        return null;
    }

    @Override
    public UserDetail getUserByToken(String token) {
//        token = token.substring(tokenHead.length());
//        return jwtTokenUtil.getUserFromToken(token);
        return null;
    }

    private Authentication authenticate(String username, String password) {
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
        }
    }
}
