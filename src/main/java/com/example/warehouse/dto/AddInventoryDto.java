package com.example.warehouse.dto;

import com.example.warehouse.entity.Inventory;
import com.example.warehouse.entity.ProductType;
import com.example.warehouse.entity.Role;
import com.example.warehouse.entity.User;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddInventoryDto {
    @NotEmpty
    @Size(max = 255)
    private String name;

    @Min(1)
    private int quantity;

    @Min(1)
    @Max(2)
    private Long product_type_id;

    public Inventory toInventory() {
        final Inventory inventory = new Inventory();

        inventory.setName(name);

        inventory.setQuantity(quantity);

        final ProductType productType = new ProductType();

        productType.setId(product_type_id);

        inventory.setProductType(productType);

        return inventory;
    }
}
