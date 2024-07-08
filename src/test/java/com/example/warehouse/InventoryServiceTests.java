package com.example.warehouse;

import com.example.warehouse.entity.Inventory;
import com.example.warehouse.entity.ProductType;
import com.example.warehouse.repository.InventoryRepository;
import com.example.warehouse.service.InventoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class InventoryServiceTests {

    @Autowired
    private InventoryService inventoryService;

    @Test
    public void testAddProduct() {
        Inventory inventory = new Inventory();
        inventory.setName("Product1");
        inventory.setQuantity(10);
        ProductType productType = new ProductType();
        productType.setTypeName("Type1");
        inventory.setProductType(productType);

        Inventory savedInventory = inventoryService.addProduct(inventory);

        assertNotNull(savedInventory);
        assertEquals("Product1", savedInventory.getName());
    }

    // Additional tests
}
