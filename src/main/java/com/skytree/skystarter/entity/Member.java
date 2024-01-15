package com.skytree.skystarter.entity;

import com.skytree.skystarter.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private long memberId;
    private String name;
    private String email;
    private String password;

    public MemberDTO toDTO() {
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(this.memberId)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .build();
        return memberDTO;
    }
}
