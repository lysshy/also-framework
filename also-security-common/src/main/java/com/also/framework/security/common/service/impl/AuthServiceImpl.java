package com.also.framework.security.common.service.impl;




import com.also.framework.security.common.domain.BaseResult;
import com.also.framework.security.common.domain.ResultCode;
import com.also.framework.security.common.domain.UserDetail;
import com.also.framework.security.common.domain.auth.BaseRole;
import com.also.framework.security.common.domain.auth.BaseUser;
import com.also.framework.security.common.domain.auth.BaseUserRole;
import com.also.framework.security.common.exception.CustomException;
import com.also.framework.security.common.service.AuthService;
import com.also.framework.security.common.service.BaseRoleService;
import com.also.framework.security.common.service.BaseUserRoleService;
import com.also.framework.security.common.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    private BaseRoleService baseRoleService;
    private BaseUserService baseUserService;
    private BaseUserRoleService baseUserRoleService;

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
            throw new CustomException(BaseResult.fail(ResultCode.BAD_REQUEST.getCode(), "用户已存在"));
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


    private Authentication authenticate(String username, String password) {
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new CustomException(BaseResult.fail(ResultCode.LOGIN_ERROR.getCode(), e.getMessage()));
        }
    }
}
