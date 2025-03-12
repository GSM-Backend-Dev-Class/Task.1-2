package com.gsm._8th.class4.backend.task12.global.security;

import com.gsm._8th.class4.backend.task12.domain.auth.filter.JwtAuthenticationFilter;
import com.gsm._8th.class4.backend.task12.domain.auth.service.CustomUserDetailsService;
import com.gsm._8th.class4.backend.task12.domain.auth.token.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(TokenProvider tokenProvider, CustomUserDetailsService customUserDetailsService){
        this.customUserDetailsService = customUserDetailsService;
        this.tokenProvider = tokenProvider;
    }

    /*
    TODO: 해당 클래스를 수정하여주세요!
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> {
                    authorize
                            .requestMatchers("/auth/**").permitAll()
                            .anyRequest().authenticated();
                })

                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement((sessionManagement) -> {
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider, customUserDetailsService), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(AbstractHttpConfigurer::disable);
        return http.build();

    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }
}
