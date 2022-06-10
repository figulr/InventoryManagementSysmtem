package com.fenikskrylo.dechallintier.feniksystem.service;

import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockUpdateDto;

import java.util.List;

public interface ProductStockService {

    Long save(ProductStockUpdateDto dto);

    ProductStockResponseDto latestLog(long barcode);

    List<ProductStockResponseDto> stockList();
}
