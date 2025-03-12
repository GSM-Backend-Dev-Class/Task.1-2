package com.gsm._8th.class4.backend.task12.domain.auth;


import com.gsm._8th.class4.backend.task12.domain.auth.dto.SIgnInRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.SignInResponse;
import org.springframework.http.ResponseEntity;

public interface SignInService {
    ResponseEntity<SignInResponse> signIn(SIgnInRequest request);
    ResponseEntity<String> getUserProfile();
}
