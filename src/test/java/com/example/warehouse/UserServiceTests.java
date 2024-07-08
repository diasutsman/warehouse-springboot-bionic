package com.example.warehouse;

import com.example.warehouse.entity.Role;
import com.example.warehouse.entity.User;
import com.example.warehouse.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("password");
        Role role = new Role();
        role.setName("ROLE_USER");
        user.setRole(role);

        User savedUser = userService.addUser(user);

        assertNotNull(savedUser);
        assertEquals("test", savedUser.getUsername());
    }

    // Additional tests
}
