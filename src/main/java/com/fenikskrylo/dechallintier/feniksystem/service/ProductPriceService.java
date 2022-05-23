package com.fenikskrylo.dechallintier.feniksystem.service;

import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductPriceResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductPriceUpdateDto;

import java.util.List;

public interface ProductPriceService {

    Long save(ProductPriceUpdateDto dto);

//   List<ProductPriceResponseDto> latestLog(long barcode);
}
