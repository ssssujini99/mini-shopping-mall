package com.programmers.zigzag.product.domain;

import com.programmers.zigzag.common.domain.TimeBaseEntity;
import com.programmers.zigzag.product.exception.ProductStockQuantityInvalidException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.*;

@Getter
@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends TimeBaseEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;

    @Column(name = "product_type", nullable = false)
    @Enumerated(STRING)
    private ProductType productType;

    @JoinColumn(name = "user_id", nullable = false)
    private Long userId;

    public Product(String name, int price, int stockQuantity, ProductType productType, Long userId) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productType = productType;
        this.userId = userId;
    }

    public void decreaseStockQuantity(int quantity) {
        if (this.stockQuantity < quantity) {
            throw new ProductStockQuantityInvalidException();
        }
        this.stockQuantity -= quantity;
    }
}
