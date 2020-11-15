package com.knoflik.services;

import com.knoflik.entities.User;
import com.knoflik.repositories.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(final String id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser() {
        User user = new User();
        user.setUsername("Igor Palych " + userRepository.count());
        userRepository.save(user);
        return user;
    }
}
