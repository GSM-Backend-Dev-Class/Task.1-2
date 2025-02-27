package com.gsm._8th.class4.backend.task12.global.error.exception;

import com.gsm._8th.class4.backend.task12.global.error.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}