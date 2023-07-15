package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface ServiceUserDAO {
    void save(User... user);

    List<User> getAllUsers();

    int deleteAll();
}
