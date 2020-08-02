package com.also.framework.security.session.config.handler;

import com.also.framework.security.common.config.property.AlsoSecurityProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private AlsoSecurityProperties properties;

    public SessionAuthenticationSuccessHandler(AlsoSecurityProperties properties) {
        this.properties = properties;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // response.setContentType("application/json;charset=utf-8");
        // response.getWriter().write(mapper.writeValueAsString(authentication));
        // SavedRequest savedRequest = requestCache.getRequest(request, response);
        // System.out.println(savedRequest.getRedirectUrl());
        // redirectStrategy.sendRedirect(request, response, savedRequest.getRedirectUrl());
        redirectStrategy.sendRedirect(request, response, properties.getLoginSuccessUrl());
    }
}
