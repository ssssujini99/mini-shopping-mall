package com.programmers.zigzag.product.controller;

import com.programmers.zigzag.product.application.ProductService;
import com.programmers.zigzag.product.application.dto.ProductDto;
import com.programmers.zigzag.product.controller.request.ProductCreateRequest;
import com.programmers.zigzag.product.controller.response.ProductCreateResponse;
import com.programmers.zigzag.product.controller.response.ProductResponse;
import com.programmers.zigzag.product.controller.response.ProductsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductCreateResponse> create(@RequestAttribute Long userId, @RequestBody ProductCreateRequest productCreateRequest) {
        ProductCreateResponse productCreateResponse = productService.createProduct(userId, productCreateRequest);
        return ResponseEntity.ok(productCreateResponse);
    }

    @GetMapping
    public ResponseEntity<ProductsResponse> getProducts(@RequestAttribute Long userId) {
        List<ProductDto> productDtoList = productService.findProductsByUserId(userId);
        return ResponseEntity.ok(getProductsResponse(productDtoList));
    }

    private static ProductsResponse getProductsResponse(List<ProductDto> productDtoList) {
        List<ProductResponse> productResponseList = productDtoList.stream()
                .map(it -> new ProductResponse(it.getId(), it.getName(), it.getPrice(), it.getStockQuantity(), it.getProductType()))
                .collect(Collectors.toList());
        return  new ProductsResponse(productResponseList);
    }
}
