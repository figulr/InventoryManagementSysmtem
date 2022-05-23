package com.fenikskrylo.dechallintier.feniksystem.domain.price;

import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductPriceResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PriceLogRepository extends JpaRepository<PriceLog, Long> {

//    Optional<List<ProductPriceResponseDto>> findFirstByBarcodeIdOrderByCreatedDateDesc(long barcode);
}
