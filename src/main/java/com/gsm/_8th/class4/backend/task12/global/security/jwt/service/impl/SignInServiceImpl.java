package com.gsm._8th.class4.backend.task12.global.security.jwt.service.impl;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.SIgnInRequest;
import com.gsm._8th.class4.backend.task12.domain.member.entity.MemberJpaEntity;
import com.gsm._8th.class4.backend.task12.domain.member.repository.MemberRepository;
import com.gsm._8th.class4.backend.task12.global.security.jwt.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignInServiceImpl {
    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;
    private final MemberService memberService;

}
