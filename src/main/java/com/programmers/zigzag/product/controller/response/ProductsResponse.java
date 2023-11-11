package com.programmers.zigzag.product.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ProductsResponse {

    List<ProductResponse> productResponseList;

}
