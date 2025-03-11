package com.gsm._8th.class4.backend.task12.global.security.jwt.service;

import com.gsm._8th.class4.backend.task12.global.security.jwt.RefreshToken;

public interface RefreshTokenService{
    RefreshToken findByRefreshToken(String refreshToken);
}
