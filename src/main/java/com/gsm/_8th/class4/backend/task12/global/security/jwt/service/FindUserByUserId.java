package com.gsm._8th.class4.backend.task12.global.security.jwt.service;

import com.gsm._8th.class4.backend.task12.domain.member.entity.MemberJpaEntity;

public interface FindUserByUserId {
    MemberJpaEntity findById(Long userId);
}
