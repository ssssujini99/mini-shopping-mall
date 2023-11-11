package com.programmers.zigzag.order.domain;

import com.programmers.zigzag.common.domain.TimeBaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends TimeBaseEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String address;

    @OneToMany(mappedBy = "order", cascade = ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @JoinColumn(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "order_price", nullable = false)
    private int orderPrice;

    public Order(String address, List<OrderProduct> orderProducts, Long userId) {
        this.address = address;
        this.orderProducts = orderProducts;
        orderProducts.forEach(it -> it.setOrder(this));
        orderProducts.forEach(it -> it.getProduct().decreaseStockQuantity(it.getPurchaseAmount()));
        this.orderPrice = orderProducts.stream().mapToInt(it -> it.getPurchasePrice()*it.getPurchaseAmount()).sum();
        this.userId = userId;
    }
}
