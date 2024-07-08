package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType productType;

    // getters and setters

}
