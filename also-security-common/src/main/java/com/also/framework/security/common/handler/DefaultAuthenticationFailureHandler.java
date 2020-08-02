package com.also.framework.security.common.handler;

import com.also.framework.common.service.MessageService;
import com.also.framework.common.utils.ResultUtils;
import com.also.framework.security.common.domain.BaseResult;
import com.also.framework.security.common.domain.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final MessageService messageService;

    public DefaultAuthenticationFailureHandler(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        BaseResult baseResult = BaseResult.fail(ResultCode.SERVER_ERROR.getCode(), messageService.getMessage(exception.getMessage()));
        ResultUtils.toJSON(response, baseResult, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
