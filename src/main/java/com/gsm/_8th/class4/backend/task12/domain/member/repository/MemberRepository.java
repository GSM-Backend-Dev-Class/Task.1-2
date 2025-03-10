package com.gsm._8th.class4.backend.task12.domain.member.repository;

import com.gsm._8th.class4.backend.task12.domain.member.entity.MemberJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberJpaEntity, Long> {
    boolean existsByEmail(String email);

}
