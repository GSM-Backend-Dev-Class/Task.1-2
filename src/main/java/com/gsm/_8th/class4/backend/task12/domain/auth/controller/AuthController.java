package com.gsm._8th.class4.backend.task12.domain.auth.controller;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.AuthRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.AuthResponse;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.RefreshRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.entity.MemberRole;
import com.gsm._8th.class4.backend.task12.domain.auth.entity.User;
import com.gsm._8th.class4.backend.task12.domain.auth.repository.UserRespository;
import com.gsm._8th.class4.backend.task12.domain.auth.service.AuthService;
import com.gsm._8th.class4.backend.task12.domain.auth.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    private ResponseEntity<String> signup(@RequestBody AuthRequest authRequest){
        return authService.signup(authRequest);
    }

    @PostMapping("/signin")
    private ResponseEntity<AuthResponse> singin(@RequestBody AuthRequest authRequest){
        return authService.signin(authRequest);
    }

    @PatchMapping("/refresh")
    private ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest){
        return authService.refresh(refreshRequest);
    }
}
