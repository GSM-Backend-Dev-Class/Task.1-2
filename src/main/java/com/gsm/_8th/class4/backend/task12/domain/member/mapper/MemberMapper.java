package com.gsm._8th.class4.backend.task12.domain.member.mapper;

import com.gsm._8th.class4.backend.task12.domain.member.domain.Member;
import com.gsm._8th.class4.backend.task12.domain.member.entity.MemberJpaEntity;
import com.gsm._8th.class4.backend.task12.global.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper implements GenericMapper<MemberJpaEntity, Member> {
    @Override
    public MemberJpaEntity toEntity(Member domain) {
        return MemberJpaEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .phoneNumber(domain.getPhoneNumber())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .address(domain.getAddress())
                .isAdult(domain.getIsAdult())
                .role(domain.getRole())
                .build();
    }

    @Override
    public Member toDomain(MemberJpaEntity jpaEntity) {
        return Member.builder()
                .id(jpaEntity.getId())
                .name(jpaEntity.getName())
                .phoneNumber(jpaEntity.getPhoneNumber())
                .email(jpaEntity.getEmail())
                .password(jpaEntity.getPassword())
                .address(jpaEntity.getAddress())
                .isAdult(jpaEntity.getIsAdult())
                .role(jpaEntity.getRole())
                .build();
    }
}