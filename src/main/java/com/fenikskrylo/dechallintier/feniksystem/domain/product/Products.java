package com.fenikskrylo.dechallintier.feniksystem.domain.product;

import com.fenikskrylo.dechallintier.feniksystem.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Products extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private long barcodeId;

    @Column(length = 500, nullable = false)
    private String productName;

    @Column(nullable = false)
    private long price;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String store;

    @Column(nullable = false)
    private String weight;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = true)
    private String volumeShort;
    @Column(nullable = true)
    private String volumeLong;
    @Column(nullable = true)
    private String volumeHeight;

    @Builder
    public Products(long id, long barcodeId, String productName, long price, String brand, String store, String weight,
                    String unit,
                    String volumeShort, String volumeLong, String volumeHeight) {
        this.id = id;
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

    @Builder
    public Products(long barcodeId, String productName, long price, String brand, String store, String weight,
                    String unit,
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

    public void update(String productName, long price, String weight, String unit, String volumeLong,
                       String volumeShort,
                       String volumeHeight) {
        this.productName = productName;
        this.price = price;
        this.weight = weight;
        this.unit = unit;
        this.volumeLong = volumeLong;
        this.volumeShort = volumeShort;
        this.volumeHeight = volumeHeight;
    }

    public void update(String productName, String weight, String volumeLong,
                       String volumeShort,
                       String volumeHeight) {
        this.productName = productName;
        this.weight = weight;
        this.volumeLong = volumeLong;
        this.volumeShort = volumeShort;
        this.volumeHeight = volumeHeight;
    }

    public void update(long price) {
        this.price = price;
    }

}
