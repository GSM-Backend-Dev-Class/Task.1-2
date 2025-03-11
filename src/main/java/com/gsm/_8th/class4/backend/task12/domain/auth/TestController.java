package com.gsm._8th.class4.backend.task12.domain.auth;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.SIgnInRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.SignInResponse;
import com.gsm._8th.class4.backend.task12.global.security.jwt.service.MemberService;
import com.gsm._8th.class4.backend.task12.global.security.jwt.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TestController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final MemberService memberService;

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SIgnInRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String accessToken = tokenService.createAccessToken(authentication.getName());
        String refreshToken = tokenService.createRefreshToken(authentication.getName());

        return ResponseEntity.status(HttpStatus.OK)
                .body(new SignInResponse(accessToken, refreshToken));
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return ResponseEntity.status(HttpStatus.OK)
                .body("Welcome to your profile, " + username + "!");
    }

    @GetMapping
    public String index() {
        return "index";
    }
}
