package com.programmers.zigzag.order.exception;

import lombok.Getter;

@Getter
public class OrderNotFoundException extends RuntimeException {

    private final long orderId;

    public OrderNotFoundException(long orderId) {
        super("orderId " + orderId + " 에 해당하는 Order가 존재하지 않습니다.");
        this.orderId = orderId;
    }
}
