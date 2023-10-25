package com.dzvonik.ordermanager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dzvonik.ordermanager.model.Order;
import com.dzvonik.ordermanager.model.OrderItem;
import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    public void testCalculateOrderPrice() {
        OrderItem item1 = mock(OrderItem.class);
        OrderItem item2 = mock(OrderItem.class);

        when(item1.getOrderItemPrice()).thenReturn(new BigDecimal("20.0"));
        when(item2.getOrderItemPrice()).thenReturn(new BigDecimal("30.0"));

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(item1);
        orderItems.add(item2);
        Order order = new Order(
                1L,
                LocalDate.now(),
                orderItems
        );

        BigDecimal orderPrice = order.calculateOrderPrice();

        assertEquals(new BigDecimal("50.0"), orderPrice);
    }
}
