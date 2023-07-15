package org.example;


import org.example.dao.ServiceUserDAOImpl;
import org.example.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(value = {SpringExtension.class})
@ContextConfiguration(classes = {HibernateConfig.class})
public class TestHibernate {

    @Autowired
    ServiceUserDAOImpl userDAO;
    @Autowired
    SessionFactory sessionFactory;

    @BeforeEach
    public void beforeEach() {
        userDAO.deleteAll();
    }

    @Test
    void saveUserTest() {
        User user = new User();
        user.setName("Fathi");
        user.setEmail("fathi.lakdhar@yahoo.fr");
        userDAO.save(user);
        List<User> users = userDAO.getAllUsers();
        Assertions.assertNotNull(users);
        Assertions.assertEquals(1, users.size());
    }

    @Test
    void saveMultiUserTest() {
        User userA = new User();
        userA.setName("userA");
        userA.setEmail("userA@yahoo.fr");

        User userB = new User();
        userB.setName("userB");
        userB.setEmail("userB@yahoo.fr");

        userDAO.save(userA, userB);
        List<User> users = userDAO.getAllUsers();
        Assertions.assertNotNull(users);
        Assertions.assertEquals(2, users.size());
    }

    @Test
    void saveMultiUserWithConstraintViolationTest() {
        User userA = new User();
        userA.setName("userA");
        userA.setEmail("userA@yahoo.fr");

        User userB = new User();
        userB.setName("userB");
        userB.setEmail(userA.getEmail());

        Assertions.assertThrows(ConstraintViolationException.class, () -> {
            userDAO.save(userA, userB);
        });

        List<User> users = userDAO.getAllUsers();
        Assertions.assertNotNull(users);
        Assertions.assertEquals(0, users.size());
    }
}
