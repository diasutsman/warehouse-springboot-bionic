package com.example.warehouse.repository;

import com.example.warehouse.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {}
