package com.also.framework.security.session.config.handler;


import com.also.framework.security.common.domain.UserDetail;
import com.also.framework.security.common.domain.auth.BaseRole;
import com.also.framework.security.common.domain.auth.BaseUser;
import com.also.framework.security.common.service.BaseRoleService;
import com.also.framework.security.common.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;


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
            throw new UsernameNotFoundException("user.username.notFound");
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
