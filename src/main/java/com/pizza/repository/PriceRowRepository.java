package com.pizza.repository;

import com.pizza.domain.entities.PriceRow;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class PriceRowRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public long create(PriceRow priceRow) {
        em.persist(priceRow);
        em.flush();
        return priceRow.getId();
    }

    public PriceRow read(long id) {
        return em.find(PriceRow.class, id);
    }

    @Transactional
    public void update(PriceRow priceRow) {
        em.merge(priceRow);
        em.flush();
    }

    @Transactional
    public void delete(long id) {
        em.remove(em.find(PriceRow.class, id));
    }

    public List<PriceRow> findAllPriceRowsForProduct(long id) {
        return em.createNamedQuery("PriceRow.findAllPricesForProductId", PriceRow.class).setParameter("product", id).getResultList();
    }

    public List<PriceRow> findAllPriceRowsForCurrency(long id) {
        return em.createNamedQuery(" PriceRow.findAllPricesForCurrencyId", PriceRow.class).setParameter("currency", id).getResultList();
    }
}
