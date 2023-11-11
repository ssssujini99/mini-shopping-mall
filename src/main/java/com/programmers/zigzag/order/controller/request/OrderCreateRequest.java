package com.programmers.zigzag.order.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderCreateRequest {

    private List<OrderProductRequest> orderProductRequestList;
    private String address;

}
