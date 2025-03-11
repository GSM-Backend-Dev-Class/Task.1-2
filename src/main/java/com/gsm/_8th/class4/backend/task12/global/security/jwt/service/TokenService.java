package com.gsm._8th.class4.backend.task12.global.security.jwt.service;

public interface TokenService {
    String createNewAccessToken(String refreshToken);
    String createAccessToken(String username);
    String createRefreshToken(String username);
}
