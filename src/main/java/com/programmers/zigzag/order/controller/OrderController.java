package com.programmers.zigzag.order.controller;

import com.programmers.zigzag.order.application.OrderService;
import com.programmers.zigzag.order.controller.request.OrderCreateRequest;
import com.programmers.zigzag.order.controller.response.OrderCreateResponse;
import com.programmers.zigzag.order.controller.response.specific.OrderSpecificResponse;
import com.programmers.zigzag.order.controller.response.OrdersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderCreateResponse> create(@RequestAttribute Long userId, @RequestBody OrderCreateRequest orderCreateRequest) {
        OrderCreateResponse orderCreateResponse = orderService.createOrder(userId, orderCreateRequest);
        return ResponseEntity.ok(orderCreateResponse);
    }

    @GetMapping
    public ResponseEntity<OrdersResponse> findOrders(@RequestAttribute Long userId) {
        OrdersResponse ordersResponse = orderService.findOrdersByUserId(userId);
        return ResponseEntity.ok(ordersResponse);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderSpecificResponse> findOrder(@PathVariable Long orderId) {
        OrderSpecificResponse orderSpecificResponse = orderService.findOrderByOrderId(orderId);
        return ResponseEntity.ok(orderSpecificResponse);
    }
}
