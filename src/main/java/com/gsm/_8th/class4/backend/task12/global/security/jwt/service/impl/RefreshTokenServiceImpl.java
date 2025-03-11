package com.gsm._8th.class4.backend.task12.global.security.jwt.service.impl;

import com.gsm._8th.class4.backend.task12.global.security.jwt.RefreshToken;
import com.gsm._8th.class4.backend.task12.global.security.jwt.RefreshTokenRepository;
import com.gsm._8th.class4.backend.task12.global.security.jwt.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow(() -> new IllegalArgumentException("Unexpected Token"));
    }
}
