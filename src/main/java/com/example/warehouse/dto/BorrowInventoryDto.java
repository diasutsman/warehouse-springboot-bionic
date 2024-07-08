package com.example.warehouse.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class BorrowInventoryDto {

    @Min(1)
    private int quantity;
}
