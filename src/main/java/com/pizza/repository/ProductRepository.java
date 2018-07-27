package com.pizza.repository;

import com.pizza.domain.entities.Product;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public long create(Product product) {
        em.persist(product);
        em.flush();
        return product.getId();
    }

    public Product read(long id) {
        return em.find(Product.class, id);
    }

    @Transactional
    public void update(Product product) {
        em.merge(product);
        em.flush();
    }

    @Transactional
    public void delete(long id) {
        em.remove(em.find(Product.class, id));
    }
}
