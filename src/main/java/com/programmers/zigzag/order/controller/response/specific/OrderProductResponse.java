package com.programmers.zigzag.order.controller.response.specific;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderProductResponse {

    private final long id;

    @JsonProperty("product")
    private final ProductResponse productResponse;

    @JsonProperty("purchase_price")
    private final int purchasePrice;

    @JsonProperty("purchase_amount")
    private final int purchaseAmount;

}
