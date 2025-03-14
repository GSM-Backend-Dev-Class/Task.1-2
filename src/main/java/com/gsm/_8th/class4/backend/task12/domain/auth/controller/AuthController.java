package com.gsm._8th.class4.backend.task12.domain.auth.controller;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.AuthRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.AuthResponse;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.RefreshRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.service.refreshservice.RefreshService;
import com.gsm._8th.class4.backend.task12.domain.auth.service.signin.SigninService;
import com.gsm._8th.class4.backend.task12.domain.auth.service.signup.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final SigninService signin;

    private final SignupService signup;

    private final RefreshService refresh;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AuthRequest authRequest){
        return signup.signup(authRequest);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody AuthRequest authRequest){
        return signin.signin(authRequest);
    }

    @PatchMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest){
        return refresh.refresh(refreshRequest);
    }
}
