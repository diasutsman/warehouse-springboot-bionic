package com.example.warehouse.repository;

import com.example.warehouse.entity.User;
import org.springframework.data.repository.ListCrudRepository;


public interface UserRepository extends ListCrudRepository<User, Long> {}
