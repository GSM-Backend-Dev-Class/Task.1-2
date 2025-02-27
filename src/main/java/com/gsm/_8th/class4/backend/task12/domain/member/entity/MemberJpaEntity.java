package com.gsm._8th.class4.backend.task12.domain.member.entity;

import com.gsm._8th.class4.backend.task12.domain.member.domain.constant.MemberRole;
import com.gsm._8th.class4.backend.task12.global.entity.BaseIdEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class MemberJpaEntity extends BaseIdEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "is_adult", nullable = false)
    private Boolean isAdult;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberRole role;

    @Builder
    public MemberJpaEntity(Long id, String name, String phoneNumber, String email, String password, String address, Boolean isAdult, MemberRole role) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.isAdult = isAdult;
        this.role = role;
    }
}