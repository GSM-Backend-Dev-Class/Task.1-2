package com.gsm._8th.class4.backend.task12.global.customException;

import org.springframework.http.HttpStatus;


public enum ErrorCode {
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"아이디를 찾을 수 없습니다"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED,"비밀번호가 일치하지 않습니다"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다"),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED,"유효하지 않은 토큰입니다"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류가 발생했습니다");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

    public String getMessage(){
        return message;
    }

}
