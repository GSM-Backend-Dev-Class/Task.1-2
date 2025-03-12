package com.gsm._8th.class4.backend.task12.domain.auth;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.SIgnInRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.SignInResponse;
import com.gsm._8th.class4.backend.task12.global.security.jwt.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public ResponseEntity<SignInResponse> signIn(SIgnInRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String accessToken = tokenService.createAccessToken(authentication.getName());
        String refreshToken = tokenService.createRefreshToken(authentication.getName());

        return ResponseEntity.status(HttpStatus.OK).body(new SignInResponse(accessToken, refreshToken));
    }

    public ResponseEntity<String> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return ResponseEntity.status(HttpStatus.OK).body("Name: "+username);
    }
}
