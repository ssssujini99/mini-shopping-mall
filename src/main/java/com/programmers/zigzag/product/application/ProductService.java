package com.programmers.zigzag.product.application;

import com.programmers.zigzag.product.application.dto.ProductDto;
import com.programmers.zigzag.product.controller.request.ProductCreateRequest;
import com.programmers.zigzag.product.controller.response.ProductCreateResponse;
import com.programmers.zigzag.product.domain.Product;
import com.programmers.zigzag.product.domain.ProductType;
import com.programmers.zigzag.product.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductCreateResponse createProduct(Long userId, ProductCreateRequest productCreateRequest) {
        Product product = new Product(productCreateRequest.getName(), productCreateRequest.getPrice(),
                productCreateRequest.getStockQuantity(), ProductType.getType(productCreateRequest.getProductType()), userId);
        productRepository.save(product);
        return new ProductCreateResponse(product.getName());
    }

    public List<ProductDto> findProductsByUserId(Long userId) {
        List<Product> productList = productRepository.findAllByUserId(userId);
        return productList.stream()
                .map(it -> new ProductDto(it.getId(), it.getName(), it.getPrice(), it.getStockQuantity(), it.getProductType().toString()))
                .collect(Collectors.toList());
    }
}
