package org.example;

import org.example.dao.ServiceUserDAO;
import org.example.dao.ServiceUserDAOImpl;
import org.example.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appCxt = new AnnotationConfigApplicationContext(HibernateConfig.class);
        ServiceUserDAO dao = appCxt.getBean(ServiceUserDAOImpl.class);
        User user = new User();
        user.setName("Fathi");
        user.setEmail("fathi.lakdhar@yahoo.fr");
        dao.save(user);
        for (User u : dao.getAllUsers()) {
            System.out.println(u.toString());
        }
    }
}