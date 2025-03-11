package com.gsm._8th.class4.backend.task12.global.security.jwt.service.impl;

import com.gsm._8th.class4.backend.task12.domain.member.repository.MemberRepository;
import com.gsm._8th.class4.backend.task12.global.security.jwt.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
