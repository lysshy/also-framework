package com.also.admin.config;

import com.alibaba.fastjson.JSON;
import com.also.framework.common.service.MessageService;
import com.also.framework.security.common.domain.BaseResult;
import com.also.framework.security.common.domain.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MyInvalidSessionStrategy implements InvalidSessionStrategy {

    private final MessageService messageService;

    public MyInvalidSessionStrategy(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json;charset=utf-8");
        BaseResult baseResult = BaseResult.fail(ResultCode.UNAUTHORIZED.getCode(), messageService.getMessage("session.invalid"));
        response.getWriter().write(JSON.toJSONString(baseResult));
    }
}
