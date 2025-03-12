package com.gsm._8th.class4.backend.task12.domain.auth.Controller;

import com.gsm._8th.class4.backend.task12.domain.auth.Service.newsignService;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.UserLoginRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.dto.UserSignupRequest;
import com.gsm._8th.class4.backend.task12.global.security.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class newsignController {

    private final newsignService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequest request) throws URISyntaxException {
        authService.signup(request);
        URI location = new URI("http://localhost:8081/api/v1/order/search/login");
        return ResponseEntity.created(location).build();
    }

}
