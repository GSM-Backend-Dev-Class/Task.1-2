package com.gsm._8th.class4.backend.task12.domain.auth.service.impl;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.AuthResponse;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.RefreshRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.service.refreshservice.RefreshService;
import com.gsm._8th.class4.backend.task12.domain.auth.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class RefreshServiceImpl implements RefreshService {

    private final TokenProvider tokenProvider;

    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refRequest){
        String refreshToken = refRequest.getRefreshToken();
        if(!tokenProvider.validateToken(refreshToken)){
            return ResponseEntity.status(403).body(null);
        }
        String userName = tokenProvider.getUsernameFromToken(refreshToken);
        tokenProvider.invalidateRefreshToken(refreshToken);
        String newAccessToken = tokenProvider.generateToken(userName);
        String newRefreshToken = tokenProvider.generateRefreshToken(userName);

        return ResponseEntity.ok(new AuthResponse(newAccessToken, newRefreshToken));
    }
}
