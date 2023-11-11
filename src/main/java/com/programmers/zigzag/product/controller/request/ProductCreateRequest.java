package com.programmers.zigzag.product.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductCreateRequest {

    private String name;
    private int price;
    private int stockQuantity;
    private String productType;
}
