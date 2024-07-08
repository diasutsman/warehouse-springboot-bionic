package com.example.warehouse.repository;

import com.example.warehouse.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {}
