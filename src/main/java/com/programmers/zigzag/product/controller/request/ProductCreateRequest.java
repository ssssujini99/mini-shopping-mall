package com.programmers.zigzag.product.controller.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductCreateRequest {

    @NotNull(message = "name은 null일 수 없습니다.")
    private String name;

    @NotNull(message = "price는 null일 수 없습니다.")
    @Positive(message = "price는 양수이어야 합니다.")
    private Integer price;

    @NotNull(message = "stockQuantity는 null일 수 없습니다.")
    @PositiveOrZero(message = "stockQuantity는 0이거나 양수이어야 합니다.")
    private Integer stockQuantity;

    @NotNull(message = "productType은 null일 수 없습니다.")
    @Pattern(regexp = "^(book|clothes)$", message = "product은 반드시 'book' 나 'clothes' 중 하나이어야 합니다.")
    private String productType;
}
