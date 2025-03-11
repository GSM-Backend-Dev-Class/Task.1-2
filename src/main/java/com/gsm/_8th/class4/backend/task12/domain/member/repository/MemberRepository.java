package com.gsm._8th.class4.backend.task12.domain.member.repository;

import com.gsm._8th.class4.backend.task12.domain.member.domain.Member;
import com.gsm._8th.class4.backend.task12.domain.member.entity.MemberJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberJpaEntity, Long> {
    Optional<MemberJpaEntity> findByEmail(String email);
}
