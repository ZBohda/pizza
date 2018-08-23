package com.pizza.repository;

import com.pizza.domain.entities.Order;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Order create(Order order) {
        em.persist(order);
        em.flush();
        return order;
    }

    public Order read(long id) {
        return em.find(Order.class, id);
    }

    @Transactional
    public void update(Order order) {
        em.merge(order);
        em.flush();
    }

    @Transactional
    public void delete(long id) {
        em.remove(em.find(Order.class, id));
    }

    public List<Order> findAllOrdersForCustomerId(long customerId) {
        return em.createNamedQuery("Order.findAllOrdersForCustomerId", Order.class).setParameter("customer", customerId).getResultList();
    }

    public List<Order> findAll() {
        return em.createNamedQuery("Order.findAll", Order.class).getResultList();
    }
}
