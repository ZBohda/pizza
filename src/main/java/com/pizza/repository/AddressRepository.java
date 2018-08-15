package com.pizza.repository;

import com.pizza.domain.entities.Address;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AddressRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public long create(Address address) {
        em.persist(address);
        em.flush();
        return address.getId();
    }

    public Address read(long id) {
        return em.find(Address.class, id);
    }

    @Transactional
    public void update(Address address) {
        em.merge(address);
        em.flush();
    }

    @Transactional
    public void delete(long id) {
        em.remove(em.find(Address.class, id));
    }

    public List<Address> getAll() {
        return em.createNamedQuery("Address.findAll", Address.class).getResultList();
    }
}
