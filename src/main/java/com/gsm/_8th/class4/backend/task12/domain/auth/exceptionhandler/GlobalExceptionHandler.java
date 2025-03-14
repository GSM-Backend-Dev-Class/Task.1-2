package com.gsm._8th.class4.backend.task12.domain.auth.exceptionhandler;

import com.gsm._8th.class4.backend.task12.global.customException.CustomException;
import com.gsm._8th.class4.backend.task12.global.customException.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex){
        ErrorCode errorCode = ex.getErrorCode();
        return new ResponseEntity<>(errorCode.getMessage(), errorCode.getHttpStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex){
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(errorCode.getMessage(), errorCode.getHttpStatus());
    }
}
