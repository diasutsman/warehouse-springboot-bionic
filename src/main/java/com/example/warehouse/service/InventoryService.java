package com.example.warehouse.service;

import com.example.warehouse.entity.BorrowingRecord;
import com.example.warehouse.entity.Inventory;
import com.example.warehouse.entity.User;
import com.example.warehouse.repository.BorrowingRecordRepository;
import com.example.warehouse.repository.InventoryRepository;
import com.example.warehouse.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;
    private BorrowingRecordRepository borrowingRecordRepository;

    public InventoryService(InventoryRepository inventoryRepository,
            BorrowingRecordRepository borrowingRecordRepository,
            UserRepository userRepository) {
        this.inventoryRepository = inventoryRepository;
        this.borrowingRecordRepository = borrowingRecordRepository;
    }

    public Inventory addProduct(Inventory inventory) {
        inventoryRepository.save(inventory);
        return inventoryRepository.findById(inventory.getId()).get();
    }

    public Inventory increaseProductQuantity(Long id, int quantity) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        inventory.setQuantity(inventory.getQuantity() + quantity);
        return inventoryRepository.save(inventory);
    }

    public Inventory borrowProduct(Long id, int quantity) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if (inventory.getQuantity() < quantity) {
            throw new RuntimeException("Not enough quantity available");
        }

        inventory.setQuantity(inventory.getQuantity() - quantity);
        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBorrowedAt(LocalDateTime.now());
        borrowingRecord.setInventory(inventory);
        borrowingRecord.setQuantityBorrowed(quantity);

        borrowingRecordRepository.save(borrowingRecord);

        return inventoryRepository.save(inventory);
    }

    public Inventory returnProduct(Long id, int quantity) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        List<BorrowingRecord> borrowingRecords = borrowingRecordRepository
                .findByInventoryIdAndQuantityBorrowed(
                        inventory.getId(),
                        quantity);

        if (borrowingRecords.size() == 0) {
            throw new RuntimeException("Product never been borrowed with the amount specified");
        }

        BorrowingRecord borrowingRecord = borrowingRecords.getFirst();

        if (borrowingRecord.getReturnedAt() == null) {
            borrowingRecord.setReturnedAt(LocalDateTime.now());
            borrowingRecordRepository.save(borrowingRecord);
        } else {
            throw new RuntimeException("Product already returned");
        }

        inventory.setQuantity(inventory.getQuantity() + quantity);

        return inventoryRepository.save(inventory);
    }

    // Additional methods
}
