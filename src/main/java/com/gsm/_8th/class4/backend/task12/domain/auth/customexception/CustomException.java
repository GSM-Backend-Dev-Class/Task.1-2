package com.gsm._8th.class4.backend.task12.domain.auth.customexception;

public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }

    public CustomException(String message, Throwable cause){
        super(message, cause);
    }
}
