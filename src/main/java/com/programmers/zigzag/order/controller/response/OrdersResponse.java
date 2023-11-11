package com.programmers.zigzag.order.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrdersResponse {

    private final List<OrderResponse> orderResponseList;
}
