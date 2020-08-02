package com.also.framework.security.common.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UnAuthenticationException extends RuntimeException {
    private String msgKey;

    public UnAuthenticationException(String msgKey) {
        super(msgKey);
        this.msgKey = msgKey;
    }
}
