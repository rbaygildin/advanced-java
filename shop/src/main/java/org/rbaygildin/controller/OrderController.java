package org.rbaygildin.controller;

import org.rbaygildin.dao.OrderDao;
import org.rbaygildin.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderDao dao;

    @Autowired
    public OrderController(OrderDao dao) {
        this.dao = dao;
    }

    @PostMapping("/users/{userId}/order")
    public Order save(@PathVariable Long userId, @RequestBody Order order) {
        return dao.save(userId, order);
    }

    @GetMapping("/users/{userId}/orders")
    public List<Order> findByUserId(@PathVariable Long userId) {
        return dao.findAll(userId);
    }
}
