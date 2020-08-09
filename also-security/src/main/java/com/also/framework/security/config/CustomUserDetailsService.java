package com.also.framework.security.config;

import com.also.framework.security.domain.auth.BaseRole;
import com.also.framework.security.domain.auth.BaseUser;
import com.also.framework.security.domain.auth.UserDetail;
import com.also.framework.security.service.BaseRoleService;
import com.also.framework.security.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 登陆身份认证
 * Author: JoeTao
 * createAt: 2018/9/14
 */
@Component(value="CustomUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final BaseUserService baseUserService;
    private final BaseRoleService baseRoleService;

    @Autowired
    public CustomUserDetailsService(BaseUserService baseUserService, BaseRoleService baseRoleService) {
        this.baseUserService = baseUserService;
        this.baseRoleService = baseRoleService;
    }

    @Override
    public UserDetail loadUserByUsername(String name) throws UsernameNotFoundException {
        BaseUser user = baseUserService.findByUsername(name);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", name));
        }
        List<BaseRole> roles = baseRoleService.selectByUserId(user.getId());
        UserDetail userDetail = new UserDetail();
        userDetail.setUsername(user.getUsername());
        userDetail.setId(user.getId());
        userDetail.setPassword(user.getPassword());
        userDetail.setRoles(roles);
        return userDetail;
    }
}
