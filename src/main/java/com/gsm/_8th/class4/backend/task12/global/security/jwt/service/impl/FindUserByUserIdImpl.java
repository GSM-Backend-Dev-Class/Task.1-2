package com.gsm._8th.class4.backend.task12.global.security.jwt.service.impl;

import com.gsm._8th.class4.backend.task12.domain.member.entity.MemberJpaEntity;
import com.gsm._8th.class4.backend.task12.domain.member.repository.MemberRepository;
import com.gsm._8th.class4.backend.task12.global.security.jwt.service.FindUserByUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserByUserIdImpl implements FindUserByUserId {
    private final MemberRepository memberRepository;

    public MemberJpaEntity findById(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Unexpected User"));
    }
}
