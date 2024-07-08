package com.example.warehouse.service;

import com.example.warehouse.entity.User;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    // Additional methods
}
