package com.gsm._8th.class4.backend.task12.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SIgnInRequest {
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
