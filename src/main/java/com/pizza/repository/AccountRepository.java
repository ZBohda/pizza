package com.pizza.repository;

import com.pizza.domain.entities.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AccountRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public long create(Account account) {
        em.persist(account);
        em.flush();
        return account.getId();
    }

    public Account read(long id) {
        return em.find(Account.class, id);
    }

    @Transactional
    public void update(Account account) {
        em.merge(account);
        em.flush();
    }

    @Transactional
    public void delete(long id) {
        em.remove(em.find(Account.class, id));
    }

    public Account findAccountByLogin(String login){
        return em.createNamedQuery("Account.getAccountByLogin", Account.class).setParameter("login", login).getResultList().stream().findFirst().orElse(null);
    }
}
