package com.programmers.zigzag.product.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductDto {

    private long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String productType;

}
