package com.gsm._8th.class4.backend.task12.domain.auth.Controller;

import com.gsm._8th.class4.backend.task12.domain.auth.Service.newsignService;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.UserLoginRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.UserSignupRequest;
import com.gsm._8th.class4.backend.task12.global.security.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class newsignController {

    private final newsignService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/api/v1/order/{orderId}/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserLoginRequest request) {
        TokenResponse tokenResponse = authService.login(request.getUsername(), request.getPassword());

        if (tokenResponse != null) {
            return ResponseEntity.ok(tokenResponse);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/api/v1/order/search/login")
    public ResponseEntity<TokenResponse> login2(@RequestBody UserLoginRequest request) {
        TokenResponse tokenResponse = authService.login(request.getUsername(), request.getPassword());

        if (tokenResponse != null) {
            return ResponseEntity.ok(tokenResponse);
        } else {
            return ResponseEntity.status(401).body(null);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshToken(@RequestHeader("Refresh-Token") String refreshToken) {
        TokenResponse newTokens = authService.refreshToken(refreshToken);

        if (newTokens != null) {
            return ResponseEntity.ok(newTokens);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserLoginRequest request) {
        try {
            authService.deleteUser(request.getUsername());
            return ResponseEntity.ok("계정 삭제 완료");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
