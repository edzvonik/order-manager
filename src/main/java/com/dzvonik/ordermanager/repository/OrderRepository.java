package com.dzvonik.ordermanager.repository;

import com.dzvonik.ordermanager.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByDateBetweenOrderByDateAsc(LocalDate startDate, LocalDate endDate);

}
