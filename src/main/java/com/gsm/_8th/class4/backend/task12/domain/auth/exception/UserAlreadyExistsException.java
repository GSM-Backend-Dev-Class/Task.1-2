package com.gsm._8th.class4.backend.task12.domain.auth.exception;

import com.gsm._8th.class4.backend.task12.global.customException.CustomException;
import com.gsm._8th.class4.backend.task12.global.customException.ErrorCode;

public class UserAlreadyExistsException extends CustomException {
    public UserAlreadyExistsException(String username){
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
