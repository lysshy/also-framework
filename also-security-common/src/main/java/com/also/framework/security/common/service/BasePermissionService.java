package com.also.framework.security.common.service;



import com.also.framework.security.common.domain.auth.BasePermission;

import java.util.List;

public interface BasePermissionService {

    List<BasePermission> selectByRoleIds(List<Long> roleIds);

    BasePermission select(String url, String methodType);
}
