package com.also.framework.security.common.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private String msgKey;

    public BadRequestException(String msgKey) {
        super(msgKey);
        this.msgKey = msgKey;
    }
}
