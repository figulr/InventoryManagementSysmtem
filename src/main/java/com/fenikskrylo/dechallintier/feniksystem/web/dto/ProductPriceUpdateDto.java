package com.fenikskrylo.dechallintier.feniksystem.web.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductPriceUpdateDto {
    private Long id;
    private long updatedPrice;
    private long barcodeId;
    private String name;
    private LocalDateTime createdDate;
}
