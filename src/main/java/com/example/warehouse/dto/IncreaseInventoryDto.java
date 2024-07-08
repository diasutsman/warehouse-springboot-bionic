package com.example.warehouse.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class IncreaseInventoryDto {

    @Min(1)
    private int quantity;
}
