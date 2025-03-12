package com.gsm._8th.class4.backend.task12.domain.auth.Service;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.UserSignupRequest;
import com.gsm._8th.class4.backend.task12.global.security.TokenResponse;
import com.gsm._8th.class4.backend.task12.domain.auth.Entity.NewSign;
import com.gsm._8th.class4.backend.task12.domain.auth.Repository.UserRepository;
import com.gsm._8th.class4.backend.task12.global.security.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class newsignService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserSignupRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("이미 존재하는 사용자입니다.");
        }
        String encoredPassword = passwordEncoder.encode(request.getPassword());
        NewSign newUser = NewSign.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role("ROLE_USER")
                .build();
        userRepository.save(newUser);
    }

    // 로그인
    public TokenResponse login(String username, String rawPassword) {
        Optional<NewSign> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            NewSign user = userOptional.get();
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                String accessToken = jwtTokenService.createAccessToken(username);
                String refreshToken = jwtTokenService.createRefreshToken(username);
                return new TokenResponse(accessToken, refreshToken);
            } else {
                throw new IllegalArgumentException("비밀번호 또는 사용자 이름이 틀립니다!");
            }
        }

        throw new IllegalArgumentException("해당 사용자 이름을 찾을 수 없습니다!");
    }

    // 리프레시 토큰을 통한 JWT 재발급
    public TokenResponse refreshToken(String refreshToken) {
        String username = jwtTokenService.getUsernameFromToken(refreshToken);
        if (username == null || !jwtTokenService.validateRefreshToken(username, refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 리프레시 토큰입니다.");
        }
        jwtTokenService.revokeRefreshToken(username);
        String newAccessToken = jwtTokenService.createAccessToken(username);
        String newRefreshToken = jwtTokenService.createRefreshToken(username);
        return new TokenResponse(newAccessToken, newRefreshToken);
    }

    // 사용자 삭제 (서비스를 통해 처리)
    @Transactional
    public void deleteUser(String username) {
        Optional<NewSign> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
        } else {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
    }
}
