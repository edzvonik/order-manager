package com.dzvonik.ordermanager.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "order_item")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id_seq")
    @SequenceGenerator(name = "order_item_id_seq", sequenceName = "order_item_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "total_price", nullable = false)
    private BigDecimal orderItemPrice;

    public OrderItem(Order order, Product product, int quantity, BigDecimal orderItemPrice) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.orderItemPrice = orderItemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (quantity != orderItem.quantity) return false;
        if (!id.equals(orderItem.id)) return false;
        if (!order.equals(orderItem.order)) return false;
        if (!product.equals(orderItem.product)) return false;
        return orderItemPrice.equals(orderItem.orderItemPrice);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + order.hashCode();
        result = 31 * result + product.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + orderItemPrice.hashCode();
        return result;
    }

}
