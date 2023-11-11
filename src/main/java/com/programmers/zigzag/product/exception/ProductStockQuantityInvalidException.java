package com.programmers.zigzag.product.exception;

public class ProductStockQuantityInvalidException extends RuntimeException {

    public ProductStockQuantityInvalidException() {
        super("재고 수량이 부족합니다.");
    }
}
