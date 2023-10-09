package com.skytree.skystarter.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesOrderSearchOption {
    Long memberId;
    Long productId;
    Long salesorderId;
    String productName;
    String memberName;
}
