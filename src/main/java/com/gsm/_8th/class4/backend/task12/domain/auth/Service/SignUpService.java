package com.gsm._8th.class4.backend.task12.domain.auth.Service;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.UserSignupRequest;

public interface SignUpService {
    void signup(UserSignupRequest request);
}
