package com.gsm._8th.class4.backend.task12.domain.auth.service.signin;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.AuthRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.AuthResponse;
import org.springframework.http.ResponseEntity;

public interface SigninService {
    ResponseEntity<AuthResponse> signin(AuthRequest authRequest);
}
