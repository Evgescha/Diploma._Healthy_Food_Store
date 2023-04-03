package com.hescha.healthystore.repository;

import com.hescha.healthystore.model.Order;
import com.hescha.healthystore.model.OrderItem;
import com.hescha.healthystore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    OrderItem findByOrder(Order order);

    OrderItem findByProduct(Product product);

    OrderItem findByCount(Integer count);
}
