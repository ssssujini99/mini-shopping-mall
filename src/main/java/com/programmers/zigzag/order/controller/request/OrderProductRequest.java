package com.programmers.zigzag.order.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderProductRequest {

    private long productId;
    private int purchasePrice;
    private int purchaseAmount;
}
