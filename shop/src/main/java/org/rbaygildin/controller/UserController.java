package org.rbaygildin.controller;

import org.rbaygildin.dao.UserDao;
import org.rbaygildin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserDao dao;

    @Autowired
    public UserController(UserDao dao) {
        this.dao = dao;
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return dao.save(user);
    }

    @PutMapping("{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return dao.update(user);
    }

    @GetMapping("{id}")
    public User find(@PathVariable Long id) {
        return dao.find(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        dao.delete(id);
    }

    @GetMapping
    public List<User> findAll() {
        return dao.findAll();
    }
}
