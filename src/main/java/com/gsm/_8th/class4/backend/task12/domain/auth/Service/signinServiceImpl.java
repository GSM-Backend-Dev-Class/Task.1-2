package com.gsm._8th.class4.backend.task12.domain.auth.Service;

import com.gsm._8th.class4.backend.task12.domain.auth.Entity.NewSign;
import com.gsm._8th.class4.backend.task12.domain.auth.Repository.UserRepository;
import com.gsm._8th.class4.backend.task12.global.security.JwtTokenService;
import com.gsm._8th.class4.backend.task12.global.security.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class signinServiceImpl implements signinService {
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenResponse login(String username, String rawPassword) {
        NewSign user = userRepository.findByUsername(username)
                .filter(u -> passwordEncoder.matches(rawPassword, u.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 틀렸습니다."));
        return new TokenResponse(
                jwtTokenService.createAccessToken(username),
                jwtTokenService.createRefreshToken(username)
        );
    }
}
