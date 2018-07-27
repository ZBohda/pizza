package com.pizza.repository;

import com.pizza.domain.entities.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public long create(Customer customer) {
        em.persist(customer);
        em.flush();
        return customer.getId();
    }

    public Customer read(long id) {
        return em.find(Customer.class, id);
    }

    @Transactional
    public void update(Customer customer) {
        em.merge(customer);
        em.flush();
    }

    @Transactional
    public void delete(long id) {
        em.remove(em.find(Customer.class, id));
    }
}
