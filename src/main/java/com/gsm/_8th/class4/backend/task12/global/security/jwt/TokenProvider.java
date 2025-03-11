package com.gsm._8th.class4.backend.task12.global.security.jwt;

import com.gsm._8th.class4.backend.task12.domain.member.entity.MemberJpaEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class TokenProvider {

    private final JwtProps props;
    private final JwtProps jwtProps;

    public String generateToken(MemberJpaEntity user, Duration expiresAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiresAt.toMillis()), user);
    }

    private String makeToken(Date expiry, MemberJpaEntity user) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE) //헤더 typ:JWT
                .setIssuedAt(now) //내용 exp
                .setExpiration(expiry) //내용 exp
                .setSubject(user.getEmail()) //내용 sub
                .claim("id", user.getId()) //클레임 id : 유저 ID
                //서명 : 비밀값과 함께 해시값을 HS256 방식으로 암호화
                .signWith(SignatureAlgorithm.HS256, props.getSecret())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(props.getSecret())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new User(claims.getSubject(), "", authorities), token, authorities);
    }

    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    private Claims getClaims(String token) {
        return Jwts.parser() //클레임 조회
                .setSigningKey(jwtProps.getSecret())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}