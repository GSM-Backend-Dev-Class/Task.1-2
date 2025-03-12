package com.gsm._8th.class4.backend.task12.domain.auth.Service;

import com.gsm._8th.class4.backend.task12.global.security.TokenResponse;

public interface RefreshTokenService {
    TokenResponse refreshToken(String refreshToken);
}
