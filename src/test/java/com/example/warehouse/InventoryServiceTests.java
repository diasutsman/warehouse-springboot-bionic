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

    @Test
    public void testIncreaseProduct() {
        Inventory inventory = new Inventory();
        inventory.setName("Product1");
        inventory.setQuantity(10);
        ProductType productType = new ProductType();
        productType.setTypeName("Type1");
        inventory.setProductType(productType);

        Inventory savedInventory = inventoryService.addProduct(inventory);

        savedInventory = inventoryService.increaseProductQuantity(savedInventory.getId(), 5);

        assertEquals(15, savedInventory.getQuantity());
    }

    @Test
    public void testBorrowAndReturnProduct() {
        Inventory inventory = new Inventory();
        inventory.setName("Product1");
        inventory.setQuantity(10);
        ProductType productType = new ProductType();
        productType.setTypeName("Type1");
        inventory.setProductType(productType);

        Inventory savedInventory = inventoryService.addProduct(inventory);

        savedInventory = inventoryService.borrowProduct(savedInventory.getId(), 5);

        assertEquals(5, savedInventory.getQuantity());

        savedInventory = inventoryService.returnProduct(savedInventory.getId(), 5);

        assertEquals(10, savedInventory.getQuantity());
    }

}
