package com.skytree.skystarter.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SalesOrderSearchOption {
    Long memberId;
    Long productId;
    Long salesorderId;
    String productName;
    String memberName;
}
