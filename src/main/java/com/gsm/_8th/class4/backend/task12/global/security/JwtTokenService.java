package com.gsm._8th.class4.backend.task12.global.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class JwtTokenService {

    @Value("${jwt.secret}")
    private String secretKey;

    private final long accessTokenValidity = 60 * 60 * 1000L;
    private final long refreshTokenValidity =  120 * 60 * 1000L;
    private final ConcurrentHashMap<String, String> refreshTokenStore = new ConcurrentHashMap<>();
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
    public String createAccessToken(String username) {
        return createToken(username, accessTokenValidity);
    }
    public String createRefreshToken(String username) {
        String refreshToken = createToken(username, refreshTokenValidity);
        refreshTokenStore.put(username, refreshToken);
        return refreshToken;
    }
    private String createToken(String subject, long validity) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validity))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // 변경됨
                .compact();
    }
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date()); // 만료 확인
        } catch (JwtException | IllegalArgumentException e) {
            log.error("유효하지 않은 토큰: {}", e.getMessage());
            return false;
        }
    }
    public String getUsernameFromToken(String token) {
        return Jwts.parser() // 변경됨
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public boolean validateRefreshToken(String username, String refreshToken) {
        return refreshToken.equals(refreshTokenStore.get(username));
    }
    public void revokeRefreshToken(String username) {
        refreshTokenStore.remove(username);
    }
}
