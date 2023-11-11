package com.programmers.zigzag.order.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderProductRequest {

    @NotNull(message = "productId는 null일 수 없습니다.")
    @PositiveOrZero(message = "productId는 0이상의 정수이어야 합니다.")
    private Long productId;

    @NotNull(message = "purchasePrice는 null일 수 없습니다.")
    @Positive(message = "purchasePrice는 양수이어야 합니다.")
    private Integer purchasePrice;

    @NotNull(message = "purchaseAmount는 null일 수 없습니다.")
    @Positive(message = "purchaseAmount는 양수이어야 합니다.")
    private Integer purchaseAmount;
}
