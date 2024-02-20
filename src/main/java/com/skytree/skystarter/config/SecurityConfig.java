package com.skytree.skystarter.config;

import com.skytree.skystarter.util.JwtRequestFilter;
import com.skytree.skystarter.util.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.UserDetailsService;


// Spring Security 설정을 위한 클래스
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // JwtTokenProvider 빈을 자동 주입
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    // UserDetailsService 빈을 자동 주입
    @Autowired
    private UserDetailsService userDetailsService;

    // JwtRequestFilter 빈을 생성
    // 생성된 빈은 Spring Security 필터 체인에 추가됨
    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        return new JwtRequestFilter(userDetailsService, jwtTokenProvider);
    }

    // Spring Security의 필터 체인을 구성하는 메소드
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                // HTTP 요청에 대한 보안 규칙을 정의
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/auth/**").permitAll() // "/auth/**" 경로는 인증 없이 접근 허용
                        .anyRequest().authenticated()) // 그 외의 모든 요청은 인증 필요
                // JwtRequestFilter를 Security 필터 체인에 추가
                .addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // 패스워드 인코더로 BCryptPasswordEncoder를 사용
    // 이는 비밀번호를 안전하게 해싱하기 위해 사용됨
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}