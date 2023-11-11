package com.programmers.zigzag.product.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse {

    @JsonProperty("product_id")
    private final long id;

    private final String name;

    private final int price;

    private final int stockQuantity;

    private String productType;
}
