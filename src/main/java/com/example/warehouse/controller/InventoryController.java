package com.example.warehouse.controller;

import com.example.warehouse.dto.AddInventoryDto;
import com.example.warehouse.dto.BorrowInventoryDto;
import com.example.warehouse.dto.IncreaseInventoryDto;
import com.example.warehouse.dto.ReturnInventoryDto;
import com.example.warehouse.entity.Inventory;
import com.example.warehouse.service.InventoryService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<Inventory> addProduct(@RequestBody @Valid AddInventoryDto addInventoryDto) {
        return ResponseEntity.ok(inventoryService.addProduct(addInventoryDto.toInventory()));
    }

    @PatchMapping("/increase/{id}")
    public ResponseEntity<Inventory> increaseProductQuantity(@PathVariable Long id,
            @RequestBody @Valid IncreaseInventoryDto quantity) {
        return ResponseEntity.ok(inventoryService.increaseProductQuantity(id, quantity.getQuantity()));
    }

    @PatchMapping("/borrow/{id}")
    public ResponseEntity<Inventory> borrowProduct(@PathVariable Long id,
            @RequestBody @Valid BorrowInventoryDto borrowInventoryDto) {

        return ResponseEntity.ok(
                inventoryService.borrowProduct(id, borrowInventoryDto.getQuantity()));
    }

    @PatchMapping("/return/{id}")
    public ResponseEntity<Inventory> returnProduct(@PathVariable Long id,
            @RequestBody @Valid ReturnInventoryDto quantity) {
        return ResponseEntity.ok(inventoryService.returnProduct(id, quantity.getQuantity()));
    }
}
