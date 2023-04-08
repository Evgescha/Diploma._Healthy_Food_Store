package com.hescha.healthystore.repository;

import com.hescha.healthystore.model.Order;
import com.hescha.healthystore.model.OrderItem;
import com.hescha.healthystore.model.OrderStatus;
import com.hescha.healthystore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderitemsContains(OrderItem orderitems);

    Order findByCreated(LocalDateTime created);

    Order findByStatus(OrderStatus status);
}
