package com.gsm._8th.class4.backend.task12.domain.auth.exceptionhandler;

import com.gsm._8th.class4.backend.task12.domain.auth.customexception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(CustomException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex){
        return new ResponseEntity<>("서버 오류가 발생했습니다", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
