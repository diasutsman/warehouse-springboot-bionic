package com.example.warehouse.dto;

import com.example.warehouse.entity.Role;
import com.example.warehouse.entity.User;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddUserDto {
    @NotEmpty
    @Size(max = 255)
    private String username;

    @NotEmpty
    @Size(max = 255)
    private String password;

    @Min(1)
    @Max(2)
    private Long role_id;

    public User toUser() {
        final User user = new User();

        user.setUsername(username);

        user.setPassword(password);

        final Role role = new Role();

        role.setId(role_id);

        user.setRole(role);

        return user;
    }
}
