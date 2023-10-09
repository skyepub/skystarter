package com.skytree.skystarter.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private long memberId;
    private String name;
    private String email;
}
