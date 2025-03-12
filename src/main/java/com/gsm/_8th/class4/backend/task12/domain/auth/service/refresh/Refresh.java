package com.gsm._8th.class4.backend.task12.domain.auth.service.refresh;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.AuthResponse;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.RefreshRequest;
import org.springframework.http.ResponseEntity;

public interface Refresh {
    ResponseEntity<AuthResponse> refresh(RefreshRequest refreshRequest);
}
