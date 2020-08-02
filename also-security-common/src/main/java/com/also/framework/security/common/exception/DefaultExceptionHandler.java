package com.also.framework.security.common.exception;

import com.also.framework.common.service.MessageService;
import com.also.framework.security.common.domain.BaseResult;
import com.also.framework.security.common.domain.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    @Autowired
    private MessageService messageService;

    /**
     * 处理所有自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public BaseResult handleCustomException(CustomException e){
        log.error(e.getBaseResult().getMsg());
        return e.getBaseResult();
    }
    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(e.getBindingResult().getFieldError().getField() + e.getBindingResult().getFieldError().getDefaultMessage());
        return BaseResult.fail(ResultCode.BAD_REQUEST.getCode(), e.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResult handleBadRequestException(BadRequestException e){
        log.error("BadRequestException", e);
        return BaseResult.fail(ResultCode.BAD_REQUEST.getCode(), messageService.getMessage(e.getMsgKey()));
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseResult handleBadRequestException(ForbiddenException e){
        log.error("ForbiddenException", e);
        return BaseResult.fail(ResultCode.FORBIDDEN.getCode(), messageService.getMessage(e.getMsgKey()));
    }

    @ExceptionHandler(UnAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public BaseResult handleUnAuthenticationException(UnAuthenticationException e){
        log.error("UnAuthenticationException", e);
        return BaseResult.fail(ResultCode.UNAUTHORIZED.getCode(), messageService.getMessage(e.getMsgKey()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public BaseResult handleAccessDeniedException(AccessDeniedException e){
        log.error("UnAuthenticationException", e);
        return BaseResult.fail(ResultCode.FORBIDDEN.getCode(), messageService.getMessage("user.permission.forbidden"));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResult handleException(Exception e){
        log.error("Exception", e);
        return BaseResult.fail(ResultCode.SERVER_ERROR.getCode(), ResultCode.SERVER_ERROR.getMsg());
    }
}
