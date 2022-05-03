package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductsUpdateRequestDto {
    private String productName;
    private String price;
    private String weight;
    private String volumeShort;
    private String volumeLong;
    private String volumeHeight;

    @Builder
    public ProductsUpdateRequestDto(String productName, String price, String weight, String volumeLong,
                                    String volumeShort,
                                    String volumeHeight){
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.volumeLong = volumeLong;
        this.volumeShort = volumeShort;
        this.volumeHeight = volumeHeight;
    }
}
