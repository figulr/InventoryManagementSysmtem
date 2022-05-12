package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import com.fenikskrylo.dechallintier.feniksystem.domain.product.Products;
import lombok.Getter;

@Getter
public class ProductsResponseDto {
    private Long id;
    private long barcodeId;
    private String productName;
    private String price;
    private String brand;
    private String weight;
    private String volumeShort;
    private String volumeLong;
    private String volumeHeight;

    public ProductsResponseDto(Products entity){
        this.id = entity.getId();
        this.barcodeId = entity.getBarcodeId();
        this.productName = entity.getProductName();
        this.price = entity.getPrice();
        this.brand = entity.getBrand();
        this.weight = entity.getWeight();
        this.volumeShort = entity.getVolumeShort();
        this.volumeLong = entity.getVolumeLong();
        this.volumeHeight = entity.getVolumeHeight();
    }
}
