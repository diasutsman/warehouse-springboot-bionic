package com.example.warehouse.service;

import com.example.warehouse.entity.Inventory;
import com.example.warehouse.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory addProduct(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory increaseProductQuantity(Long id, int quantity) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        inventory.setQuantity(inventory.getQuantity() + quantity);
        return inventoryRepository.save(inventory);
    }

    public Inventory borrowProduct(Long id, int quantity) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        if (inventory.getQuantity() < quantity) {
            throw new RuntimeException("Not enough quantity available");
        }
        inventory.setQuantity(inventory.getQuantity() - quantity);
        return inventoryRepository.save(inventory);
    }

    public Inventory returnProduct(Long id, int quantity) {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        inventory.setQuantity(inventory.getQuantity() + quantity);
        return inventoryRepository.save(inventory);
    }

    // Additional methods
}
