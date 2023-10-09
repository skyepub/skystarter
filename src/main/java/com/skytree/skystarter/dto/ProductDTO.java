package com.skytree.skystarter.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private long productId;
    private String name;
    private long price;
    private String desc;
}
