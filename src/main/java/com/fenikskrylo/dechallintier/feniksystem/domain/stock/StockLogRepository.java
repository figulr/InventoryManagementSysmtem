package com.fenikskrylo.dechallintier.feniksystem.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StockLogRepository extends JpaRepository<StockLog, Long> {
    // List<StockLog> findTop5ByOrderByCreatedDateDesc();

    Optional<StockLog> findFirstByBarcodeIdOrderByCreatedDateDesc(long barcode);

    Optional<List<StockLog>> findByCreatedDateBetweenOrderByCreatedDateDesc(LocalDateTime startDate,
                                                                            LocalDateTime endDate);

    @Query(value = "select s from StockLog s")
    Optional<List<StockLog>> find();
}
