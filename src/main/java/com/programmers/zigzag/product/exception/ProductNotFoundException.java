package com.programmers.zigzag.product.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {

    private final long productId;

    public ProductNotFoundException(long productId) {
        super("productId " + productId + " 에 해당하는 Product가 존재하지 않습니다.");
        this.productId = productId;
    }
}
