package com.example.warehouse.dataloader;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.warehouse.entity.Role;
import com.example.warehouse.entity.Roles;
import com.example.warehouse.repository.RoleRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RoleDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RoleDataLoader.class);

    private final RoleRepository roleRepository;
    private final ObjectMapper objectMapper;

    public RoleDataLoader(RoleRepository roleRepository, ObjectMapper objectMapper) {
        this.roleRepository = roleRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() != 0) {
            log.info("Not loading roles from json file because table is already filled");
            return;
        }

        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/roles.json")) {
            Roles roles = objectMapper.readValue(inputStream, Roles.class);
            log.info("Reading {} roles from json file", roles.roles().size());
            roleRepository.saveAll(roles.roles());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read json data", e);
        }
    }

}
