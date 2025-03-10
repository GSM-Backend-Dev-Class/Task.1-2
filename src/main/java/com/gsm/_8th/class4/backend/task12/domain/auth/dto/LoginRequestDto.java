package com.gsm._8th.class4.backend.task12.domain.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginRequestDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}
