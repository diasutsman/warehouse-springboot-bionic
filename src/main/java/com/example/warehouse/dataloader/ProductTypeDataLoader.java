package com.example.warehouse.dataloader;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.warehouse.entity.ProductTypes;
import com.example.warehouse.entity.Role;
import com.example.warehouse.entity.Roles;
import com.example.warehouse.repository.ProductTypeRepository;
import com.example.warehouse.repository.RoleRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ProductTypeDataLoader implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ProductTypeDataLoader.class);

    private final ProductTypeRepository productTypeRepository;
    private final ObjectMapper objectMapper;

    public ProductTypeDataLoader(ProductTypeRepository roleRepository, ObjectMapper objectMapper) {
        this.productTypeRepository = roleRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        if (productTypeRepository.count() != 0) {
            log.info("Not loading product types from json file because table is already filled");
            return;
        }

        try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/product-types.json")) {
            ProductTypes productTypes = objectMapper.readValue(inputStream, ProductTypes.class);
            log.info("Reading {} product types from json file", productTypes.productTypes().size());
            productTypeRepository.saveAll(productTypes.productTypes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read json data", e);
        }
    }

}
