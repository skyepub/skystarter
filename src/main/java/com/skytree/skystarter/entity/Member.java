package com.skytree.skystarter.entity;

import com.skytree.skystarter.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private long memberId;
    private String name;
    private String email;

    public MemberDTO toDTO() {
        MemberDTO memberDTO = MemberDTO.builder()
                .memberId(this.memberId)
                .name(this.name)
                .email(this.email)
                .build();
        return memberDTO;
    }
}
