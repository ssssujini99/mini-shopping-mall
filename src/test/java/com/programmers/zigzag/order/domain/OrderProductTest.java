package com.programmers.zigzag.order.domain;

import com.programmers.zigzag.product.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.programmers.zigzag.product.domain.ProductType.BOOK;
import static org.junit.jupiter.api.Assertions.*;

class OrderProductTest {

    @DisplayName("주문 상품의 총 주문 상품 금액을 계산할 수 있다.")
    @Test
    void testCalculateTotalCost() {
        // given
        int purchasePrice = 10000;
        int purchaseAmount = 2;
        Product product = new Product("토비의 스프링", 10000, 5, BOOK, 1L);
        OrderProduct orderProduct = new OrderProduct(product, purchasePrice, purchaseAmount);

        // when
        int totalCost = orderProduct.calculateTotalCost();

        // then
        assertEquals(totalCost, purchasePrice * purchaseAmount);
    }
}
