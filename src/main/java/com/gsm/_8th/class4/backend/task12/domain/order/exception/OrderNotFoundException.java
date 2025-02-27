package com.gsm._8th.class4.backend.task12.domain.order.exception;

import com.gsm._8th.class4.backend.task12.global.error.ErrorCode;
import com.gsm._8th.class4.backend.task12.global.error.exception.CustomException;

public class OrderNotFoundException extends CustomException {
    public OrderNotFoundException() {
        super(ErrorCode.ORDER_NOT_FOUND);
    }
}