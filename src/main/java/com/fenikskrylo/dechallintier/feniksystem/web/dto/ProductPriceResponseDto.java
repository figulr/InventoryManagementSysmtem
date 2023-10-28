package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import com.fenikskrylo.dechallintier.feniksystem.domain.price.PriceLog;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductPriceResponseDto {
    private long barcodeId;
    private long updatedPrice;
    private LocalDateTime createdDate;
    private boolean history = true;

    public ProductPriceResponseDto(PriceLog entity) {
        this.barcodeId = entity.getBarcodeId();
        this.updatedPrice = entity.getUpdatedPrice();
        this.createdDate = entity.getCreatedDate();
        this.history = true;
    }

    public ProductPriceResponseDto(boolean result) {
        this.history = result;
    }
}
