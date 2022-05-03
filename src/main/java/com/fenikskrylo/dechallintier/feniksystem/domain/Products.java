package com.fenikskrylo.dechallintier.feniksystem.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Products extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private long barcodeId;

    @Column(length = 500, nullable = false)
    private String productName;

    @Column(nullable = false)
    private String price;

    @Column(nullable = false)
    private String brand;

//    @Column(nullable = false)
//    private PurchaseAt store;

    @Column(nullable = false)
    private String weight;

    @Column(nullable = false)
    private String volumeShort;
    @Column(nullable = false)
    private String volumeLong;
    @Column(nullable = false)
    private String volumeHeight;

    @Builder
    public Products(long barcodeId, String productName, String price, String brand, String weight, String volumeShort, String volumeLong, String volumeHeight) {
        this.barcodeId = barcodeId;
        this.productName = productName;
        this.price = price;
        this.brand = brand;
        this.weight = weight;
        this.volumeShort = volumeShort;
        this.volumeLong = volumeLong;
        this.volumeHeight = volumeHeight;
    }

    public void update(String productName, String price, String weight, String volumeLong, String volumeShort,
                       String volumeHeight){
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.volumeLong = volumeLong;
        this.volumeShort = volumeShort;
        this.volumeHeight = volumeHeight;
    }

}
