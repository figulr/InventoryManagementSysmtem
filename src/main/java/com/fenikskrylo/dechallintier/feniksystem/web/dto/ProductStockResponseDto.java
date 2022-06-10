package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import com.fenikskrylo.dechallintier.feniksystem.domain.stock.StockLog;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductStockResponseDto {
    private int inStock;
    private int stockAdd;
    private int stockSub;
    private long barcodeId;
    private String name;
    private String productName;

    public ProductStockResponseDto(StockLog stockLog, String productName){
        this.barcodeId = stockLog.getBarcodeId();
        this.productName = productName;
        this.inStock = stockLog.getInStock();
    }
}
