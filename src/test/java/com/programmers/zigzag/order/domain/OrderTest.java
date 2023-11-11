package com.programmers.zigzag.order.domain;

import com.programmers.zigzag.product.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.programmers.zigzag.product.domain.ProductType.BOOK;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @DisplayName("주문의 총 주문 금액을 계산할 수 있다.")
    @Test
    void testGetOrderPrice() {
        // given
        Product product1 = new Product("토비의 스프링", 10000, 5, BOOK, 1L);
        Product product2 = new Product("토비의 스프링2", 20000, 4, BOOK, 1L);
        OrderProduct orderProduct1 = new OrderProduct(product1, 10000, 2);
        OrderProduct orderProduct2 = new OrderProduct(product2, 20000, 3);
        Order order = new Order("서울특별시", List.of(orderProduct1, orderProduct2), 1L);

        // when
        int orderPrice = order.getOrderPrice();

        // then
        assertEquals(orderPrice, orderProduct1.calculateTotalCost() + orderProduct2.calculateTotalCost());
    }
}
