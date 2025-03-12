package com.gsm._8th.class4.backend.task12.domain.auth.Controller;

import com.gsm._8th.class4.backend.task12.domain.auth.Service.DeleteUserService;
import com.gsm._8th.class4.backend.task12.domain.auth.Service.LoginService;
import com.gsm._8th.class4.backend.task12.domain.auth.Service.NewSignService;
import com.gsm._8th.class4.backend.task12.domain.auth.Service.RefreshTokenService;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.UserLoginRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.UserSignupRequest;
import com.gsm._8th.class4.backend.task12.global.security.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/auth") // ✅ API 명세서에 맞춰 변경
@RequiredArgsConstructor
public class newsignController {  // ✅ 클래스명 변경

    private final NewSignService authService;
    private final DeleteUserService deleteUserService;
    private final LoginService loginService;
    private final RefreshTokenService refreshTokenService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequest request) throws URISyntaxException {
        authService.signup(request);
        URI location = new URI("http://localhost:8081/api/v1/auth/signin");
        return ResponseEntity.created(location).build();
    }

    // 회원 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestBody UserLoginRequest request) {
        deleteUserService.deleteUser(request.getUsername());
        return ResponseEntity.ok("계정 삭제 완료");
    }

    // 로그인
    @PostMapping("/signin") // ✅ API 경로 수정
    public ResponseEntity<TokenResponse> login(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(loginService.login(request.getUsername(), request.getPassword()));
    }

    // 토큰 갱신
    @PostMapping("/refresh") // ✅ API 경로 유지
    public ResponseEntity<TokenResponse> refreshToken(@RequestHeader("Refresh-Token") String refreshToken) {
        return ResponseEntity.ok(refreshTokenService.refreshToken(refreshToken));
    }
}
