package com.gsm._8th.class4.backend.task12.domain.auth;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.SIgnInRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.SignInResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SignInService signInService;

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SIgnInRequest request) {
        return signInService.signIn(request);
    }

    @GetMapping("/profile")
    public ResponseEntity<String> getUserProfile() {
        return signInService.getUserProfile();
    }
}
