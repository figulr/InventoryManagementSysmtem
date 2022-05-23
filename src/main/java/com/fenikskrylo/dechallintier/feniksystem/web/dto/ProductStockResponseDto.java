package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import lombok.*;

@Builder
@Data
public class ProductStockResponseDto {
    private int inStock;
    private int stockAdd;
    private int stockSub;
    private long barcodeId;
    private String name;
}
