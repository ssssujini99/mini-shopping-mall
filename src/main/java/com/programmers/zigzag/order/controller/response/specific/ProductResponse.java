package com.programmers.zigzag.order.controller.response.specific;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponse {

    private final long id;
    private final String name;
    private final String productType;
}
