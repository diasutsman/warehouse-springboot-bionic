package com.example.warehouse.service;

import com.example.warehouse.entity.User;
import com.example.warehouse.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId()).get();
    }

    // Additional methods
}
