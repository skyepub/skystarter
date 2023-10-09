package com.skytree.skystarter.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalesOrderComplex01DTO {
    private long salesorderId;
    private long memberId;
    private long productId;
    LocalDateTime createDate;
    private String memberName;
    private String productName;
    private long price;
    private long quantity;
}