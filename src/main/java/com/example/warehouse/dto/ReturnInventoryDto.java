package com.example.warehouse.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ReturnInventoryDto {

    @Min(1)
    private int quantity;
}
