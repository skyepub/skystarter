package com.skytree.skystarter.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberDTO {
    private long memberId;
    private String name;
    private String email;
}
