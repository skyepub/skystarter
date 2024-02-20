package com.skytree.skystarter.util;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// OncePerRequestFilter를 상속받은 JwtRequestFilter 정의
public class JwtRequestFilter extends OncePerRequestFilter {

    // UserDetailsService와 JwtTokenProvider를 주입받음
    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    // 생성자를 통해 주입된 UserDetailsService와 JwtTokenProvider 초기화
    public JwtRequestFilter(UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 필터의 주 로직을 정의하는 메소드
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // HTTP 요청에서 'Authorization' 헤더를 가져옴
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // 'Authorization' 헤더가 존재하고, 'Bearer '로 시작하는 경우
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // 'Bearer '를 제거하여 실제 JWT를 추출
            jwt = authorizationHeader.substring(7);
            try {
                // JWT로부터 사용자 이름을 추출
                username = jwtTokenProvider.extractUsername(jwt);
            } catch (ExpiredJwtException e) {
                // JWT가 만료된 경우의 예외 처리
                System.out.println("ExpiredJwtException e");
            } catch (Exception e) {
                // 기타 예외 처리
                System.out.println("Exception e");
            }
        }

        // 사용자 이름이 null이 아니고, 현재 SecurityContext에 인증 정보가 없는 경우
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // UserDetailsService를 사용하여 사용자 정보를 불러옴
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // JWT가 유효한지 확인
            if (jwtTokenProvider.validateToken(jwt, username)) {
                // 유효한 경우, UsernamePasswordAuthenticationToken을 생성하여 인증을 진행
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // SecurityContext에 인증 정보를 설정
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // 다음 필터로 요청과 응답을 전달
        chain.doFilter(request, response);
    }
}