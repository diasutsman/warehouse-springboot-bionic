package com.example.warehouse.repository;

import com.example.warehouse.entity.ProductType;
import org.springframework.data.repository.ListCrudRepository;

public interface ProductTypeRepository extends ListCrudRepository<ProductType, Long> {}
