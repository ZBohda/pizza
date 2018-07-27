package com.pizza.repository;

import com.pizza.domain.entities.Currency;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CurrencyRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public long create(Currency currency) {
        em.persist(currency);
        em.flush();
        return currency.getId();
    }

    public Currency read(long id) {
        return em.find(Currency.class, id);
    }

    @Transactional
    public void update(Currency currency) {
        em.merge(currency);
        em.flush();
    }

    @Transactional
    public void delete(long id) {
        em.remove(em.find(Currency.class, id));
    }
}
