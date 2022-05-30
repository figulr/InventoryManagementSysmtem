package com.fenikskrylo.dechallintier.feniksystem.service;

import com.fenikskrylo.dechallintier.feniksystem.domain.price.PriceLog;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductPriceResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductPriceUpdateDto;

import java.util.List;

public interface ProductPriceService {

    Long save(ProductPriceUpdateDto dto);

    boolean checkHistory(long barcode);

    List<ProductPriceResponseDto> latestLog(long barcode);
}
