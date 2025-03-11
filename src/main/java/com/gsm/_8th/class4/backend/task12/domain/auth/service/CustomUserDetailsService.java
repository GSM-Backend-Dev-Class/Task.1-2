package com.gsm._8th.class4.backend.task12.domain.auth.service;

import com.gsm._8th.class4.backend.task12.domain.auth.entity.User;
import com.gsm._8th.class4.backend.task12.domain.auth.repository.UserRespository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRespository userRespository;

    public CustomUserDetailsService(UserRespository userRespository){
        this.userRespository = userRespository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        User user = userRespository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을수 없습니다"));
        return org.springframework.security.core.userdetails.User.withUsername(user.getUsername()).password(user.getPassword()).authorities("ROLE_USER").build();
    }
}
