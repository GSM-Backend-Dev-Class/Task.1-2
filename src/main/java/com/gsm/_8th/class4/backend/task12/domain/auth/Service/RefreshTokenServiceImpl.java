package com.gsm._8th.class4.backend.task12.domain.auth.Service;

import com.gsm._8th.class4.backend.task12.global.security.JwtTokenService;
import com.gsm._8th.class4.backend.task12.global.security.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final JwtTokenService jwtTokenService;

    @Override
    public TokenResponse refreshToken(String refreshToken) {
        String username = Optional.ofNullable(jwtTokenService.getUsernameFromToken(refreshToken))
                .filter(u -> jwtTokenService.validateRefreshToken(u, refreshToken))
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다."));


        jwtTokenService.revokeRefreshToken(username);
        return new TokenResponse(
                jwtTokenService.createAccessToken(username),
                jwtTokenService.createRefreshToken(username)
        );
    }
}
