package com.fenikskrylo.dechallintier.feniksystem.service;

import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockUpdateDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductStockService {

    Long save(ProductStockUpdateDto dto);

    ProductStockResponseDto latestLog(long barcode);

    List<ProductStockResponseDto> stockList();

    List<ProductStockResponseDto> dailyStockLog(LocalDate date);
}
