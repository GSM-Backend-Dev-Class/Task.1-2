package com.gsm._8th.class4.backend.task12.global.error.handler;

import com.gsm._8th.class4.backend.task12.global.error.dto.response.ErrorResponse;
import com.gsm._8th.class4.backend.task12.global.error.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(new ErrorResponse(e.getErrorCode().getMessage(), e.getErrorCode().getHttpStatus()));
    }
}