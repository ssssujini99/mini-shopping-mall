package com.programmers.zigzag.order.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderCreateRequest {

    @JsonProperty("order_product_list")
    @NotNull(message = "order_product_list는 null일 수 없습니다.")
    @Size(min = 1, max = 10, message = "상품은 한번에 최소 1개 최대 10개까지 주문할 수 있습니다.")
    @Valid
    private List<OrderProductRequest> orderProductRequestList;

    @NotNull(message = "address는 null일 수 없습니다.")
    private String address;

}
