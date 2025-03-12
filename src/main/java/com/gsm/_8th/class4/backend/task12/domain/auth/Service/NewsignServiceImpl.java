package com.gsm._8th.class4.backend.task12.domain.auth.Service;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.UserSignupRequest;
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
public class NewsignServiceImpl implements NewSignService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void signup(UserSignupRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        NewSign newUser = NewSign.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role("ROLE_USER")
                .build();

        userRepository.save(newUser);
    }
}
