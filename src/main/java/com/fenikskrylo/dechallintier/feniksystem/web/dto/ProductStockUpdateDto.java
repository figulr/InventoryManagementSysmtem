package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductStockUpdateDto {
    private int inStock;
    private int stockAdd;
    private int stockSub;
    private String name;
    private long barcodeId;
}
