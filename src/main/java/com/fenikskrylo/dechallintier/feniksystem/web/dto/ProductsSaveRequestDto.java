package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import com.fenikskrylo.dechallintier.feniksystem.domain.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class ProductsSaveRequestDto {
    private long barcodeId;
    private String productName;
    private String price;
    private String brand;
//    private PurchaseAt store;
    private String weight;
    private String volumeShort;
    private String volumeLong;
    private String volumeHeight;

    @Builder
    public ProductsSaveRequestDto(long barcodeId, String productName, String price, String brand, String weight,
                                  String volumeShort, String volumeLong, String volumeHeight){
        this.barcodeId = barcodeId;
        this.productName = productName;
        this.price = price;
        this.brand = brand;
        this.weight = weight;
        this.volumeShort = volumeShort;
        this.volumeLong = volumeLong;
        this.volumeHeight = volumeHeight;
    }

    public Products toEntity(){
        return Products.builder()
                .barcodeId(barcodeId)
                .productName(productName)
                .price(price)
                .brand(brand)
                .weight(weight)
                .volumeShort(volumeShort)
                .volumeLong(volumeLong)
                .volumeHeight(volumeHeight)
                .build();
    }
}
