package com.also.framework.security.common.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ForbiddenException extends RuntimeException {

    private String msgKey;

    public ForbiddenException(String msgKey) {
        super(msgKey);
        this.msgKey = msgKey;
    }
}
