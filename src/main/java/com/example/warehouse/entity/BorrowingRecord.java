package com.example.warehouse.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    private int quantityBorrowed;
    private LocalDateTime borrowedAt;
    private LocalDateTime returnedAt;

    // getters and setters
}
