package com.also.framework.security.common.handler;



import com.also.framework.security.common.constants.PermissionConstant;
import com.also.framework.security.common.domain.UserDetail;
import com.also.framework.security.common.domain.auth.BasePermission;
import com.also.framework.security.common.domain.auth.BaseRole;
import com.also.framework.security.common.service.BasePermissionService;
import com.also.framework.security.common.service.BaseRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultAuthorityService implements AuthorityService {

    private final BaseRoleService baseRoleService;

    private final BasePermissionService basePermissionService;

    public DefaultAuthorityService(BaseRoleService baseRoleService, BasePermissionService basePermissionService) {
        this.baseRoleService = baseRoleService;
        this.basePermissionService = basePermissionService;
    }

    public boolean checkPermission(HttpServletRequest request, Authentication authentication) {
        //首先校验有没有登录
        Object userInfo = authentication.getPrincipal();
        if (!(userInfo instanceof UserDetails)) {
            //表示未登录
            return false;
        }

        //如果url在permission表中没有，则不鉴权，默认有权限
        String url = getRequestPath(request);
        String methodType = request.getMethod();
        BasePermission basePermission = basePermissionService.select(url, methodType);
        if (basePermission == null) {
            return true;
        }

        UserDetail principal = (UserDetail) userInfo;
        long userId = principal.getId();

        List<BaseRole> roles = baseRoleService.selectByUserId(userId);
        List<Long> roleIds = roles.stream()
                .map(BaseRole::getId)
                .collect(Collectors.toList());
        List<BasePermission> permissions = basePermissionService.selectByRoleIds(roleIds);

        //获取资源，前后端分离，所以过滤页面权限，只保留按钮权限
        List<BasePermission> btnPerms = permissions.stream()
                // 过滤页面权限
                .filter(permission -> Objects.equals(permission.getType(), PermissionConstant.TYPE_BUTTON))
                // 过滤 URL 为空
                .filter(permission -> StringUtils.isNotBlank(permission.getUrl()))
                // 过滤 METHOD 为空
                .filter(permission -> StringUtils.isNotBlank(permission.getMethodType()))
                .collect(Collectors.toList());

        for (BasePermission btnPerm : btnPerms) {
            AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(btnPerm.getUrl(), btnPerm.getMethodType());
            if (antPathMatcher.matches(request)) {
                return true;
            }
        }

        return false;
    }

    private String getRequestPath(HttpServletRequest request) {
        String url = request.getServletPath();
        String pathInfo = request.getPathInfo();
        if (pathInfo != null) {
            url = StringUtils.isNotEmpty(url) ? url + pathInfo : pathInfo;
        }

        return url;
    }
}