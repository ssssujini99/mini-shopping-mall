package com.programmers.zigzag.product.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProductType {

    BOOK("book");

    private final String value;

    ProductType(String value) {
        this.value = value;
    }

    public static ProductType getType(String value) {
        return Arrays.stream(ProductType.values())
                .filter(type -> type.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
