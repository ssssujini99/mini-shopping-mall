package com.programmers.zigzag.order.application;

import com.programmers.zigzag.order.controller.request.OrderCreateRequest;
import com.programmers.zigzag.order.controller.request.OrderProductRequest;
import com.programmers.zigzag.order.controller.response.OrderCreateResponse;
import com.programmers.zigzag.order.domain.Order;
import com.programmers.zigzag.order.persistence.OrderRepository;
import com.programmers.zigzag.product.domain.Product;
import com.programmers.zigzag.product.persistence.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.concurrent.*;

import static com.programmers.zigzag.product.domain.ProductType.BOOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * 주문 동시성 테스트
 *
 * 동시에 여러 주문이 일어날 때, 재고감소 및 주문개수 확인
 */
@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class OrderServiceConcurrencyTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderService orderService;

    @DisplayName("동시에 여러 주문이 들어와도, 재고 감소가 일어난다.")
    @Test
    void testCreateOrder() throws InterruptedException, ExecutionException {
        // given
        int threadCount = 100;
        int stockQuantity = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        Product product = new Product("product1", 10000, stockQuantity, BOOK, 1L);
        Product savedProduct = productRepository.save(product);
        Long productId = savedProduct.getId();

        OrderProductRequest orderProductRequest = new OrderProductRequest(productId, 10000, 1);
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest(List.of(orderProductRequest), "address");

        // when
        List<Future<OrderCreateResponse>> futures = executorService.invokeAll(getCreateOrderCallables(threadCount, orderCreateRequest));
        Set<Long> orderSet = new HashSet<>();
        for (Future<OrderCreateResponse> future : futures) {
            orderSet.add(future.get().getOrderId());
        }

        Product resultProduct = productRepository.findById(productId).get();
        List<Order> orderList = orderRepository.findAll();

        // then
        assertAll(
                () -> assertThat(orderList).hasSize(threadCount),
                () -> assertThat(resultProduct.getStockQuantity()).isZero()
        );
    }

    private List<Callable<OrderCreateResponse>> getCreateOrderCallables(int threadCount, OrderCreateRequest orderCreateRequest) {
        List<Callable<OrderCreateResponse>> callables = new ArrayList<>();
        for(int i=0; i<threadCount; i++) {
            int finalI = i;
            callables.add(() -> orderService.createOrder(finalI, orderCreateRequest));
        }
        return callables;
    }
}
