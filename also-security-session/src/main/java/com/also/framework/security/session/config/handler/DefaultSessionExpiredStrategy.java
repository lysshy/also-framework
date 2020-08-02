package com.also.framework.security.session.config.handler;

import com.also.framework.common.service.MessageService;
import com.also.framework.common.utils.ResultUtils;
import com.also.framework.security.common.domain.BaseResult;
import com.also.framework.security.common.domain.ResultCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class DefaultSessionExpiredStrategy implements SessionInformationExpiredStrategy {

    private final MessageService messageService;

    public DefaultSessionExpiredStrategy(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        HttpServletResponse response = event.getResponse();
        BaseResult baseResult = BaseResult.fail(ResultCode.UNAUTHORIZED.getCode(), messageService.getMessage("session.kick"));
        ResultUtils.toJSON(response, baseResult, HttpStatus.UNAUTHORIZED);
    }
}
