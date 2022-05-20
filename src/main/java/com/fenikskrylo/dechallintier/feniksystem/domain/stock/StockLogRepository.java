package com.fenikskrylo.dechallintier.feniksystem.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockLogRepository extends JpaRepository<StockLog, Long> {
//    List<StockLog> findTop5ByOrderByCreatedDateDesc();

    Optional<StockLog> findFirstByBarcodeIdOrderByCreatedDateDesc(long barcode);
}
