package com.gsm._8th.class4.backend.task12.domain.member.domain;

import com.gsm._8th.class4.backend.task12.domain.member.domain.constant.MemberRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Member {
    public final Long id;
    public final String name;
    public final String email;
    public final String password;
    public final String phoneNumber;
    public final String address;
    public final Boolean isAdult;
    public final MemberRole role;
}
