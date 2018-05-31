package org.rbaygildin.dao;

import org.rbaygildin.model.Order;
import org.rbaygildin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private final EntityManager em;

    @Autowired
    public OrderDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public Order save(Long userId, Order order) {
        User user = em.find(User.class, userId);
        user.getOrders().add(order);
        em.persist(user);
        return order;
    }

    @Override
    @Transactional
    public Order update(Order order) {
        return em.merge(order);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Order order = em.find(Order.class, id);
        em.detach(order);
    }

    @Override
    public Order find(Long id) {
        return em.find(Order.class, id);
    }

    @Override
    public List<Order> findAll(Long userId) {
        return em.createQuery("from Order join fetch Product").getResultList();
    }
}
