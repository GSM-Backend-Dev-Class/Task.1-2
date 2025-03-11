package com.gsm._8th.class4.backend.task12.domain.auth.controller;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.AuthRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.AuthResponse;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.RefreshRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.entity.User;
import com.gsm._8th.class4.backend.task12.domain.auth.repository.UserRespository;
import com.gsm._8th.class4.backend.task12.domain.auth.token.TokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserRespository userRespository;
    private BCryptPasswordEncoder passwordEncoder;
    private TokenProvider tokenProvider;

    public AuthController(UserRespository userRespository, BCryptPasswordEncoder passwordEncoder, TokenProvider tokenProvider){
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.userRespository = userRespository;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AuthRequest authRequest){
        Optional<User> existingUser = userRespository.findByUsername(authRequest.getUsername());
        if(existingUser.isPresent()){
            return ResponseEntity.badRequest().body("이미 존재하는 아이디입니다");
        }

        String encodedPassword = passwordEncoder.encode(authRequest.getPassword());
        User user = new User(authRequest.getUsername(), encodedPassword);
        userRespository.save(user);

        return ResponseEntity.status(201).body("회원가입이 완료되었습니다");
    }

    @PostMapping("/signin")
    private ResponseEntity<AuthResponse> signin(@RequestBody AuthRequest authRequest) {
        Optional<User> userOptional = userRespository.findByUsername(authRequest.getUsername());

        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        User user = userOptional.get();
        if (!passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body(null);
        }

        String accessToken = tokenProvider.generateToken(user.getUsername());
        String refreshToken = tokenProvider.generateRefreshToken(user.getUsername());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PatchMapping("/refresh")
    private ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refRequest){
        String refreshToken = refRequest.getRefreshToken();
        if(!tokenProvider.validateToken(refreshToken)){
            return ResponseEntity.status(403).body(null);
        }
        String userName = tokenProvider.getUsernameFromToken(refreshToken);
        tokenProvider.invalidateRefreshToken(refreshToken);
        String newAccessToken = tokenProvider.generateToken(userName);
        String newRefreshToken = tokenProvider.generateRefreshToken(userName);

        return ResponseEntity.ok(new AuthResponse(newAccessToken, newRefreshToken));
    }
}
