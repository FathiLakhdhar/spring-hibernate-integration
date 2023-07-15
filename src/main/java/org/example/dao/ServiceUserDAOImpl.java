package org.example.dao;

import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ServiceUserDAOImpl implements ServiceUserDAO {


    private SessionFactory sessionFactory;

    @Autowired
    public ServiceUserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void save(User... users) {
        Session session = sessionFactory.getCurrentSession();
        for (User user : users) {
            session.save(user);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Transactional
    @Override
    public int deleteAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("DELETE FROM User").executeUpdate();
    }
}
