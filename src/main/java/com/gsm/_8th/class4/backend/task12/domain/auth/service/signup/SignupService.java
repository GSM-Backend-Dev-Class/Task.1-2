package com.gsm._8th.class4.backend.task12.domain.auth.service.signup;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.AuthRequest;
import org.springframework.http.ResponseEntity;

public interface SignupService {
    ResponseEntity<String> signup(AuthRequest authRequest);
}
