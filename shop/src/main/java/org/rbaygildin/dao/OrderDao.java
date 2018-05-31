package org.rbaygildin.dao;

import org.rbaygildin.model.Order;

import java.util.List;

public interface OrderDao {
    Order save(Long userId, Order order);

    Order update(Order order);

    void delete(Long id);

    Order find(Long id);

    List<Order> findAll(Long userId);
}
