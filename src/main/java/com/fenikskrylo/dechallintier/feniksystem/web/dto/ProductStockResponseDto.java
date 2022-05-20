package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import com.fenikskrylo.dechallintier.feniksystem.domain.product.Products;
import com.fenikskrylo.dechallintier.feniksystem.domain.stock.StockLog;
import com.fenikskrylo.dechallintier.feniksystem.domain.user.User;
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
