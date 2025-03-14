package com.gsm._8th.class4.backend.task12.domain.auth.exception;

import com.gsm._8th.class4.backend.task12.global.customException.CustomException;
import com.gsm._8th.class4.backend.task12.global.customException.ErrorCode;

public class AuthenticationFailedException extends CustomException {
    public AuthenticationFailedException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}
