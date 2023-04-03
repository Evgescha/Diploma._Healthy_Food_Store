package com.hescha.healthystore.service;

import com.hescha.healthystore.model.Order;
import com.hescha.healthystore.model.OrderItem;
import com.hescha.healthystore.model.Product;
import com.hescha.healthystore.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService extends CrudService<OrderItem> {

    private final OrderItemRepository repository;

    public OrderItemService(OrderItemRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public OrderItem findByOrder(Order order) {
        return repository.findByOrder(order);
    }

    public OrderItem findByProduct(Product product) {
        return repository.findByProduct(product);
    }

    public OrderItem findByCount(Integer count) {
        return repository.findByCount(count);
    }


    public OrderItem update(Long id, OrderItem entity) {
        OrderItem read = read(id);
        if (read == null) {
            throw new RuntimeException("Entity OrderItem not found");
        }
        updateFields(entity, read);
        return update(read);

    }

    private void updateFields(OrderItem entity, OrderItem read) {
        read.setOrder(entity.getOrder());
        read.setProduct(entity.getProduct());
        read.setCount(entity.getCount());
    }
}
