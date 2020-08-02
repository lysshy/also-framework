package com.also.framework.security.common.exception;

import com.also.framework.security.common.domain.BaseResult;
import lombok.Getter;


@Getter
public class CustomException extends RuntimeException{

    private BaseResult baseResult;

    public CustomException(BaseResult baseResult) {
        this.baseResult = baseResult;
    }
}
