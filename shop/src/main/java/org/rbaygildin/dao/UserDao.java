package org.rbaygildin.dao;

import org.rbaygildin.model.User;

import java.util.List;

public interface UserDao {
    User save(User user);
    User update(User user);
    void delete(Long id);
    User find(Long id);
    List<User> findAll();
}
