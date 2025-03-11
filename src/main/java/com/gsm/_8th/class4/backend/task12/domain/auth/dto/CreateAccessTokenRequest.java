package com.gsm._8th.class4.backend.task12.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateAccessTokenRequest {
    private String refreshToken;
}
