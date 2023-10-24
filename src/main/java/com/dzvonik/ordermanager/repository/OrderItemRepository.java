package com.dzvonik.ordermanager.repository;

import com.dzvonik.ordermanager.model.Order;
import com.dzvonik.ordermanager.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrder(Order order);

    void removeAllByOrder(Order order);

}
