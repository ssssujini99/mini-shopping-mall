package com.programmers.zigzag.order.controller.response.specific;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class OrderSpecificResponse {

    private final long id;

    private final List<OrderProductResponse> orderProductResponseList;

    private final int orderPrice;

    private final String address;

}
