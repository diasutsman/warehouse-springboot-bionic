package com.example.warehouse.controller;

import com.example.warehouse.entity.Inventory;
import com.example.warehouse.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Inventory> addProduct(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.addProduct(inventory));
    }

    @PutMapping("/increase/{id}")
    public ResponseEntity<Inventory> increaseProductQuantity(@PathVariable Long id, @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.increaseProductQuantity(id, quantity));
    }

    @PutMapping("/borrow/{id}")
    public ResponseEntity<Inventory> borrowProduct(@PathVariable Long id, @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.borrowProduct(id, quantity));
    }

    @PutMapping("/return/{id}")
    public ResponseEntity<Inventory> returnProduct(@PathVariable Long id, @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.returnProduct(id, quantity));
    }

    // Additional endpoints
}
