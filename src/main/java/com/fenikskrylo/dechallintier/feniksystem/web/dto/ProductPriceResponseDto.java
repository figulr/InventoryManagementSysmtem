package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ProductPriceResponseDto {
    private long barcodeId;
    private long updatedPrice;
    private LocalDateTime createdDate;
    private boolean history = true;
}
