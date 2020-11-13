package com.knoflik.rest;


import com.knoflik.entities.User;
import com.knoflik.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public final class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable final String id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public User create(@RequestBody final User user) {
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void clearUserById(@PathVariable final String id) {
        userRepository.deleteById(id);
    }

    @DeleteMapping("/")
    public void clearUsers() {
        userRepository.deleteAll();
    }
}
