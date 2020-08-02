package com.also.framework.security.session.config.handler;

import com.also.framework.common.service.MessageService;
import com.also.framework.common.utils.ResultUtils;
import com.also.framework.security.common.domain.BaseResult;
import com.also.framework.security.common.domain.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DefaultInvalidSessionStrategy implements InvalidSessionStrategy {

    private final MessageService messageService;

    public DefaultInvalidSessionStrategy(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BaseResult baseResult = BaseResult.fail(ResultCode.UNAUTHORIZED.getCode(), messageService.getMessage("session.invalid"));
        ResultUtils.toJSON(response, baseResult, HttpStatus.UNAUTHORIZED);
    }
}
