package com.gsm._8th.class4.backend.task12.domain.auth.Service;

import com.gsm._8th.class4.backend.task12.domain.auth.Entity.NewSign;
import com.gsm._8th.class4.backend.task12.global.security.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gsm._8th.class4.backend.task12.domain.auth.Repository.UserRepository;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class deleteUserImpl implements DeleteUserService {
    private final UserRepository userRepository;

    @Override
    @Transactional

    public void deleteUser(String username) {
        userRepository.findByUsername(username)
                .ifPresentOrElse(
                        userRepository::delete,
                        () -> { throw new IllegalArgumentException("존재하지 않는 사용자입니다."); }
                );
}
}
