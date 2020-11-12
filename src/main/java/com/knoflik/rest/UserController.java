package com.knoflik.rest;


import com.knoflik.entities.User;
import com.knoflik.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public User create(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public void clearUserById(@PathVariable String id) {
        userRepository.deleteById(id);
    }

    @DeleteMapping("/")
    public void clearUsers() {
        userRepository.deleteAll();
    }
}
