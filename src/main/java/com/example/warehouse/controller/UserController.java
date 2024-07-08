package com.example.warehouse.controller;

import com.example.warehouse.dto.AddUserDto;
import com.example.warehouse.entity.User;
import com.example.warehouse.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addUser(/** Validation api runs every time the run instance get created */
    @RequestBody @Valid AddUserDto addUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(addUserDto.toUser()));
    }

    // Additional endpoints
}
