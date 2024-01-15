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
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/get_token")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        // 사용자 인증 (예: UserDetailsService 를 사용하여 사용자 정보를 검증)
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        // 데이터베이스에서 가져온 저장된 암호
        String storedPassword = userDetails.getPassword();

        // 입력된 패스워드를 해싱하여 저장된 패스워드와 비교
        if (!storedPassword.matches(password)) {
            // 패스워드가 일치하지 않는 경우 BadCredentialsException 발생
            throw new BadCredentialsException("Bad credentials");
        }

        // 사용자가 인증되면 JWT 토큰을 생성하여 반환
        String token = jwtTokenProvider.generateToken(username);
        return token;
    }
}
