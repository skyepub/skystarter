package com.skytree.skystarter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.skytree.skystarter.util.JwtTokenProvider;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider; // JWT 토큰 제공자

    @Autowired
    private UserDetailsService userDetailsService; // 사용자 상세 서비스

    // 사용자 이름과 패스워드를 받아 JWT 토큰을 생성하는 엔드포인트
    @GetMapping("/get_token")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // UserDetailsService를 사용하여 데이터베이스에서 사용자 정보를 가져옴
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // 데이터베이스에 저장된 암호화된 패스워드
        String storedPassword = userDetails.getPassword();

        // 입력된 패스워드와 데이터베이스에 저장된 패스워드 비교 (여기서는 단순 문자열 매칭, 실제로는 암호화된 패스워드를 비교해야 함)
        if (!storedPassword.equals(password)) {
            // 패스워드가 일치하지 않는 경우, BadCredentialsException 발생
            throw new BadCredentialsException("Bad credentials");
        }

        // 사용자 인증이 성공하면 JWT 토큰을 생성하여 반환
        String token = jwtTokenProvider.generateToken(username);
        return token;
    }
}