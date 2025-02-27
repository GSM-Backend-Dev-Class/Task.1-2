package com.gsm._8th.class4.backend.task12.domain.member.domain.constant;

import org.springframework.security.core.GrantedAuthority;

public enum MemberRole implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}