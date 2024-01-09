package com.skytree.skystarter.dto;

import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SalesOrderComplexDTO {
    private long salesorderId;
    private long memberId;
    private long productId;
    LocalDateTime createDate;
    private String memberName;
    private String productName;
    private long price;
    private long quantity;
}