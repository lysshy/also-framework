package com.also.framework.security.common.handler;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface AuthorityService {

    boolean checkPermission(HttpServletRequest request, Authentication authentication);
}
