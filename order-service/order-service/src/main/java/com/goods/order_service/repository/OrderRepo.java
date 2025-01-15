package com.goods.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goods.order_service.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

}
