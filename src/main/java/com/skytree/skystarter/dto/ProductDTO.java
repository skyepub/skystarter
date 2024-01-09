package com.skytree.skystarter.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDTO {
    private long productId;
    private String name;
    private long price;
    private String desc;
}
