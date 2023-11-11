package com.programmers.zigzag.order.application;

import com.programmers.zigzag.order.controller.request.OrderCreateRequest;
import com.programmers.zigzag.order.controller.request.OrderProductRequest;
import com.programmers.zigzag.order.controller.response.OrderCreateResponse;
import com.programmers.zigzag.order.controller.response.OrderResponse;
import com.programmers.zigzag.order.controller.response.specific.OrderProductResponse;
import com.programmers.zigzag.order.controller.response.specific.OrderSpecificResponse;
import com.programmers.zigzag.order.controller.response.OrdersResponse;
import com.programmers.zigzag.order.controller.response.specific.ProductResponse;
import com.programmers.zigzag.order.domain.Order;
import com.programmers.zigzag.order.domain.OrderProduct;
import com.programmers.zigzag.order.exception.OrderNotFoundException;
import com.programmers.zigzag.order.persistence.OrderRepository;
import com.programmers.zigzag.product.domain.Product;
import com.programmers.zigzag.product.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderCreateResponse createOrder(long userId, OrderCreateRequest orderCreateRequest) {
        List<Long> productIdList = orderCreateRequest.getOrderProductRequestList().stream()
                .map(OrderProductRequest::getProductId)
                .collect(Collectors.toList());
        List<Product> productList = productRepository.findAllById(productIdList);
        Map<Long, Product> hashMap = getHashMap(productList);
        List<OrderProduct> orderProductList = orderCreateRequest.getOrderProductRequestList().stream()
                .map(it -> new OrderProduct(hashMap.get(it.getProductId()), it.getPurchasePrice(), it.getPurchaseAmount()))
                .collect(Collectors.toList());
        Order order = new Order(orderCreateRequest.getAddress(), orderProductList, userId);
        Order savedOrder = orderRepository.save(order);
        return new OrderCreateResponse(savedOrder.getId());
    }

    @Transactional(readOnly = true)
    public OrdersResponse findOrdersByUserId(Long userId) {
        List<Order> orderList = orderRepository.findByUserId(userId);
        List<OrderResponse> orderResponseList = orderList.stream()
                .map(it -> new OrderResponse(it.getId(), it.getOrderPrice(), it.getCreatedAt(), it.getUpdatedAt()))
                .collect(Collectors.toList());
        return new OrdersResponse(orderResponseList);
    }

    @Transactional(readOnly = true)
    public OrderSpecificResponse findOrderByOrderId(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }
        Order order = optionalOrder.get();
        List<OrderProductResponse> orderProductResponseList = order.getOrderProducts().stream()
                .map(it -> new OrderProductResponse(it.getId(), new ProductResponse(it.getProduct().getId(), it.getProduct().getName(), it.getProduct().getProductType().toString()), it.getPurchasePrice(), it.getPurchaseAmount()))
                .collect(Collectors.toList());
        return new OrderSpecificResponse(order.getId(), orderProductResponseList, order.getOrderPrice(), order.getAddress());
    }

    public Map<Long, Product> getHashMap(List<Product> productList) {
        Map<Long, Product> hashMap = new HashMap<>();
        productList.forEach(it -> hashMap.put(it.getId(), it));
        return hashMap;
    }
}