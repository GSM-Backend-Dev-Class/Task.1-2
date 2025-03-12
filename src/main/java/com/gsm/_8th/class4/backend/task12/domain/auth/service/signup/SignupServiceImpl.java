package com.gsm._8th.class4.backend.task12.domain.auth.service.signup;

import com.gsm._8th.class4.backend.task12.domain.auth.dto.AuthRequest;
import com.gsm._8th.class4.backend.task12.domain.auth.entity.MemberRole;
import com.gsm._8th.class4.backend.task12.domain.auth.entity.User;
import com.gsm._8th.class4.backend.task12.domain.auth.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String> signup(AuthRequest authRequest){
        Optional<User> existingUser = userRespository.findByUsername(authRequest.getUsername());
        if(existingUser.isPresent()){
            return ResponseEntity.badRequest().body("이미 존재하는 아이디입니다");
        }

        String encodedPassword = passwordEncoder.encode(authRequest.getPassword());
        User user = new User(authRequest.getUsername(), encodedPassword, MemberRole.ROLE_USER);
        userRespository.save(user);

        return ResponseEntity.status(201).body("회원가입이 완료되었습니다");
    }
}
