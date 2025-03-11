package com.gsm._8th.class4.backend.task12.domain.auth.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenProvider {

    private final Key key;
    private final long expirationTime = 1000 * 60 * 60;
    private final long refreshExpirationTime = 1000 * 60 * 60 * 7;
    private final ConcurrentHashMap<String, Boolean> refreshTokenBlacklist = new ConcurrentHashMap<>();

    public TokenProvider(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.ES256)
                .compact();
    }

    public String generateRefreshToken(String username){
        String refreshToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        refreshTokenBlacklist.put(refreshToken, false);
        return refreshToken;
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public String getUsernameFromToken(String token){
        return Jwts.parser().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public void     invalidateRefreshToken(String token){
        refreshTokenBlacklist.put(token, true);
    }
}
