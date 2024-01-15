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

    private final MemberRepository memberRepository;

    @Autowired
    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 여기서 사용자 정보를 데이터베이스에서 조회하고 UserDetails로 변환하여 반환
        Member member = memberRepository.findByEmail(username);
        if (member == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Member 엔티티 정보를 UserDetails로 변환하여 반환하는 코드 작성
        // 예를 들어, 아래와 같이 UserDetails를 생성할 수 있습니다.
         UserDetails userDetails = new User(member.getEmail(), member.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));

        // 실제 코드에 따라 UserDetails를 생성하고 반환해야 합니다.
        return userDetails;
    }
}
