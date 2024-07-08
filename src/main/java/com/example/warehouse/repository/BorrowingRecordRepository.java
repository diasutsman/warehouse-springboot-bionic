package com.example.warehouse.repository;

import com.example.warehouse.entity.BorrowingRecord;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

public interface BorrowingRecordRepository extends ListCrudRepository<BorrowingRecord, Long> {
    @Query("FROM BorrowingRecord WHERE inventory.id = :inventoryId AND quantityBorrowed = :quantity AND returnedAt IS NULL")
    List<BorrowingRecord> findByInventoryIdAndQuantityBorrowed(@Param("inventoryId") Long inventoryId,
            @Param("quantity") int quantity);
}
