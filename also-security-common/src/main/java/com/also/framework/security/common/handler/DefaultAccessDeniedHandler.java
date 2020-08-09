package com.also.framework.security.common.handler;

import com.also.framework.common.service.MessageService;
import com.also.framework.common.utils.ResultUtils;
import com.also.framework.security.common.domain.BaseResult;
import com.also.framework.security.common.domain.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultAccessDeniedHandler implements AccessDeniedHandler {

    private final MessageService messageService;

    public DefaultAccessDeniedHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response, AccessDeniedException e) throws IOException {
        //登陆状态下，权限不足执行该方法
        BaseResult baseResult = BaseResult.fail(ResultCode.FORBIDDEN.getCode(), messageService.getMessage("user.access.deny"));
        ResultUtils.toJSON(response, baseResult, HttpStatus.FORBIDDEN);
    }
}