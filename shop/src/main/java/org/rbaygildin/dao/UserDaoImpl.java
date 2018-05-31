package org.rbaygildin.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.rbaygildin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    @Transactional
    public User update(User user) {
        em.merge(user);
        return user;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Query query = em.createQuery("from User where id=:id");
        query.setParameter("id", id);
        User user = (User) query.getSingleResult();
        em.remove(user);
    }

    @Override
    public User find(Long id) {
        Query query = em.createQuery("from User where id=:id");
        query.setParameter("id", id);
        try {
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        Query query = em.createQuery("from User");
        return (List<User>) query.getResultList();
    }
}
