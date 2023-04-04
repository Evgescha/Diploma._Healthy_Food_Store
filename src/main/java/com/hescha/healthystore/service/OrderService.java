package com.hescha.healthystore.service;

import com.hescha.healthystore.model.Order;
import com.hescha.healthystore.model.OrderItem;
import com.hescha.healthystore.model.OrderStatus;
import com.hescha.healthystore.model.User;
import com.hescha.healthystore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService extends CrudService<Order> {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Order findByOwner(User owner) {
        return repository.findByOwner(owner);
    }

    public List<Order> findByOrderitemsContains(OrderItem orderitems) {
        return repository.findByOrderitemsContains(orderitems);
    }

    public Order findByCreated(LocalDateTime created) {
        return repository.findByCreated(created);
    }

    public Order findByStatus(OrderStatus status) {
        return repository.findByStatus(status);
    }


    public Order update(Long id, Order entity) {
        Order read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity Order not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(Order entity, Order read) {
        read.setOrderitems(entity.getOrderitems());
        read.setStatus(entity.getStatus());
    }
}
