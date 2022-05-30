package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import com.fenikskrylo.dechallintier.feniksystem.domain.Unit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductsUpdateRequestDto {
    private String productName;
    private long price;
    private String weight;
    private String unit;
    private String volumeShort;
    private String volumeLong;
    private String volumeHeight;


    @Builder
    public ProductsUpdateRequestDto(String productName, long price, String weight, String unit, String volumeLong,
                                    String volumeShort,
                                    String volumeHeight){
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.unit = unit;
        this.volumeLong = volumeLong;
        this.volumeShort = volumeShort;
        this.volumeHeight = volumeHeight;
    }
    @Builder
    public ProductsUpdateRequestDto(String productName, String weight,  String volumeLong,
                                    String volumeShort,
                                    String volumeHeight){
        this.productName = productName;
        this.weight = weight;
        this.volumeLong = volumeLong;
        this.volumeShort = volumeShort;
        this.volumeHeight = volumeHeight;
    }
}
