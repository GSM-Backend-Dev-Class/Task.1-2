package com.gsm._8th.class4.backend.task12.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {

    @NotNull @Size(min = 2, max = 10)
    private String name;
    @NotNull
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull @Size(min = 5, max = 20)
    private String password;
    @NotNull @Size(min = 13, max = 13)
    private String phoneNumber;
    @NotNull
    private String address;
    @NotNull
    private boolean isAdult;
}
