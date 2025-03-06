package com.gsm._8th.class4.backend.task12.domain.auth.Controller;

import com.gsm._8th.class4.backend.task12.domain.auth.Repository.UserRepository;
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
    private final UserRepository userRepository;
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequest request) {
        TokenResponse tokenResponse = authService.login(request.getUsername(), request.getPassword());

        if (tokenResponse != null) {
            return ResponseEntity.ok(tokenResponse);
        } else {
            return ResponseEntity.status(401).body("로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("Refresh-Token") String refreshToken) {
        TokenResponse newTokens = authService.refreshToken(refreshToken);

        if (newTokens != null) {
            return ResponseEntity.ok(newTokens);
        } else {
            return ResponseEntity.status(403).body("유효하지 않은 리프레시 토큰입니다.");
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody UserLoginRequest request) {
        TokenResponse tokenResponse = authService.login(request.getUsername(), request.getPassword());

        if (tokenResponse != null) {
            return userRepository.findByUsername(request.getUsername())
                    .map(user -> {
                        userRepository.delete(user);
                        return ResponseEntity.ok("계정 삭제 완료");
                    })
                    .orElse(ResponseEntity.status(404).body("사용자를 찾을 수 없습니다."));
        } else {
            return ResponseEntity.status(401).body("로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
        }
    }
}
