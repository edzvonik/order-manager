package com.dzvonik.ordermanager;

import com.dzvonik.ordermanager.model.Order;
import com.dzvonik.ordermanager.model.OrderItem;
import com.dzvonik.ordermanager.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderItemTest {

    @Test
    public void testOrderItemConstructor() {
        Order order = mock(Order.class);
        Product product = mock(Product.class);

        when(product.getPrice()).thenReturn(new BigDecimal("10.0"));
        when(order.getId()).thenReturn(1L);

        OrderItem orderItem = new OrderItem(order, product, 3);

        assertEquals(order, orderItem.getOrder());
        assertEquals(product, orderItem.getProduct());
        assertEquals(3, orderItem.getQuantity());
        assertEquals(new BigDecimal("30.0"), orderItem.getOrderItemPrice());
    }

    @Test
    public void testChangeQuantityAndCalcItemPrice() {
        Order order = mock(Order.class);
        Product product = mock(Product.class);

        when(product.getPrice()).thenReturn(new BigDecimal("10.0"));
        when(order.getId()).thenReturn(1L);

        OrderItem orderItem = new OrderItem(order, product, 3);
        orderItem.changeQuantity(4);

        assertEquals(4, orderItem.getQuantity());
        assertEquals(new BigDecimal("40.0"), orderItem.getOrderItemPrice());
    }

}
