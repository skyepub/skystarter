package com.skytree.skystarter.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret; // JWT 생성에 사용되는 비밀키

    @Value("${jwt.expiration}")
    private long jwtExpiration; // JWT의 만료 시간 (초 단위)

    // 사용자 이름을 기반으로 JWT를 생성하는 메소드
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration * 1000); // 만료 시간 계산 (밀리초로 변환)

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username); // 'sub' (subject) 클레임에 사용자 이름 추가

        // JWT 생성
        return Jwts.builder()
                .setClaims(claims) // 클레임 설정
                .setSubject(username) // 주제 설정
                .setIssuedAt(now) // 발급 시간 설정
                .setExpiration(expiryDate) // 만료 시간 설정
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // 서명 알고리즘과 비밀키 사용
                .compact(); // JWT 생성
    }

    // 토큰의 유효성 검증 (사용자 이름과 만료 시간을 체크)
    public Boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    // 토큰에서 사용자 이름 추출
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // 토큰에서 만료 시간 추출
    public Date extractExpiration(String token) {
        return extractClaims(token).getExpiration();
    }

    // 토큰에서 클레임 추출
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret) // 서명 키 설정
                .parseClaimsJws(token) // 토큰 파싱
                .getBody(); // 클레임 반환
    }

    // 토큰의 만료 여부 확인
    public boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        return expiration.before(new Date()); // 현재 시간보다 만료 시간이 이전이면 만료된 것임
    }
}