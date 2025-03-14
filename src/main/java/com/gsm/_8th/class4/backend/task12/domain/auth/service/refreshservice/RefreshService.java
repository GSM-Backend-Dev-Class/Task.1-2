package com.gsm._8th.class4.backend.task12.domain.auth.service.refreshservice;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.AuthResponse;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.RefreshRequest;
import org.springframework.http.ResponseEntity;

public interface RefreshService {
    ResponseEntity<AuthResponse> refresh(RefreshRequest refreshRequest);
}
