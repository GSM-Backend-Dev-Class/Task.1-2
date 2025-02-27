package com.gsm._8th.class4.backend.task12.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    ORDER_NOT_FOUND("주문을 찾을 수 없습니다.", 404);

    private final String message;
    private final int httpStatus;
}