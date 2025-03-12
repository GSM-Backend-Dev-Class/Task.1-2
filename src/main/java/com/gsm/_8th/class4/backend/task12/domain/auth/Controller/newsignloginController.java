package com.gsm._8th.class4.backend.task12.domain.auth.Controller;

import com.gsm._8th.class4.backend.task12.domain.auth.Service.newsignService;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.UserLoginRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.UserSignupRequest;
import com.gsm._8th.class4.backend.task12.global.security.TokenResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class newsignloginController {
    private final newsignService authService;
    @PostMapping("/order/{orderId}/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserLoginRequest request) {
        TokenResponse tokenResponse = authService.login(request.getUsername(), request.getPassword());

        if (tokenResponse != null) {
            return ResponseEntity.ok(tokenResponse);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }
}
