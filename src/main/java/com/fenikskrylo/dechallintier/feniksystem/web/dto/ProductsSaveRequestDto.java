package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import com.fenikskrylo.dechallintier.feniksystem.domain.product.Products;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductsSaveRequestDto {
    private long barcodeId;
    private String productName;
    private long price;
    private String brand;
    private String store;
    private String weight;
    private String unit;
    private String volumeShort;
    private String volumeLong;
    private String volumeHeight;

    @Builder
    public ProductsSaveRequestDto(long barcodeId, String productName, long price, String brand,
                                  String store, String weight, String unit,
                                  String volumeShort, String volumeLong, String volumeHeight) {
        this.barcodeId = barcodeId;
        this.productName = productName;
        this.price = price;
        this.brand = brand;
        this.store = store;
        this.weight = weight;
        this.unit = unit;
        this.volumeShort = volumeShort;
        this.volumeLong = volumeLong;
        this.volumeHeight = volumeHeight;
    }

    public Products toEntity() {
        return Products.builder()
                .barcodeId(barcodeId)
                .productName(productName)
                .brand(brand)
                .store(store)
                .price(price)
                .weight(weight)
                .unit(unit)
                .volumeShort(volumeShort)
                .volumeLong(volumeLong)
                .volumeHeight(volumeHeight)
                .build();

    }
}
