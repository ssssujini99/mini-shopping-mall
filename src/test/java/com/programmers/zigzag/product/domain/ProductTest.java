package com.programmers.zigzag.product.domain;

import com.programmers.zigzag.product.exception.ProductStockQuantityInvalidException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.programmers.zigzag.product.domain.ProductType.BOOK;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @DisplayName("재고 수량보다 구매 수량이 많을 경우 예외가 발생한다.")
    @Test
    void testDecreaseStockQuantity() {
        // given
        Product product = new Product("토비의 스프링", 10000, 2, BOOK, 1L);

        // when

        // then
        assertThrows(ProductStockQuantityInvalidException.class,
                () -> product.decreaseStockQuantity(3));
    }

    @DisplayName("재고 수량과 구매 수량이 같을 경우에는 예외가 발생하지 않는다.")
    @Test
    void testDecreaseStockQuantityWithEqualQuantity() {
        // given
        Product product = new Product("토비의 스프링", 10000, 2, BOOK, 1L);

        // when

        // then
        assertDoesNotThrow(() -> product.decreaseStockQuantity(2));
    }
}
