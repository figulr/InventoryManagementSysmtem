package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import com.fenikskrylo.dechallintier.feniksystem.domain.product.Products;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProductsResponseDto {
    private Long id;
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
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private boolean registered = true;

    public ProductsResponseDto(Products entity){
        this.id = entity.getId();
        this.barcodeId = entity.getBarcodeId();
        this.productName = entity.getProductName();
        this.brand = entity.getBrand();
        this.store = entity.getStore();
        this.price = entity.getPrice();
        this.weight = entity.getWeight();
        this.unit = entity.getUnit();
        this.volumeShort = entity.getVolumeShort();
        this.volumeLong = entity.getVolumeLong();
        this.volumeHeight = entity.getVolumeHeight();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

    public ProductsResponseDto(boolean result){
        this.registered = result;
    }
}
