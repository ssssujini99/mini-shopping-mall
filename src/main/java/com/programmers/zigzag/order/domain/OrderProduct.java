package com.programmers.zigzag.order.domain;

import com.programmers.zigzag.common.domain.TimeBaseEntity;
import com.programmers.zigzag.product.domain.Product;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Table(name = "order_product")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct extends TimeBaseEntity {

    @Id
    @Column(name = "order_product_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "purchase_price", nullable = false)
    private int purchasePrice;

    @Column(name = "purchase_amount", nullable = false)
    private int purchaseAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderProduct(Product product, int purchasePrice, int purchaseAmount) {
        this.product = product;
        this.purchasePrice = purchasePrice;
        this.purchaseAmount = purchaseAmount;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int calculateTotalCost() {
        return this.purchasePrice * this.purchaseAmount;
    }

}
