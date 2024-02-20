package com.skytree.skystarter.service;

import com.skytree.skystarter.entity.Member;
import com.skytree.skystarter.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // MemberRepository 인스턴스에 대한 참조
    private final MemberRepository memberRepository;

    // 생성자를 통해 MemberRepository 주입
    @Autowired
    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // loadUserByUsername 메소드 오버라이드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 데이터베이스에서 사용자 정보를 조회
        Member member = memberRepository.findByEmail(username);
        if (member == null) {
            // 사용자 정보가 없는 경우 UsernameNotFoundException 예외 발생
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // 조회된 Member 객체를 UserDetails 객체로 변환
        // 이 예제에서는 사용자 이름, 비밀번호 및 권한을 포함한 UserDetails 객체를 생성
        UserDetails userDetails = new User(member.getEmail(), member.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));

        // 생성된 UserDetails 객체 반환
        return userDetails;
    }
}