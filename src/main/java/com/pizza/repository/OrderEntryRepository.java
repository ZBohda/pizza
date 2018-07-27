package com.pizza.repository;

import com.pizza.domain.entities.OrderEntry;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class OrderEntryRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public long create(OrderEntry orderEntry) {
        em.persist(orderEntry);
        em.flush();
        return orderEntry.getId();
    }

    public OrderEntry read(long id) {
        return em.find(OrderEntry.class, id);
    }

    @Transactional
    public void update(OrderEntry orderEntry) {
        em.merge(orderEntry);
        em.flush();
    }

    @Transactional
    public void delete(long id) {
        em.remove(em.find(OrderEntry.class, id));
    }
}
