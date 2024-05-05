package com.example.orders.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findByOrderIdentifier_OrderId(String orderId);

    void deleteOrderByOrderIdentifier_OrderId(String orderId);

    boolean existsOrderByOrderIdentifier_OrderId(String orderId);
}
