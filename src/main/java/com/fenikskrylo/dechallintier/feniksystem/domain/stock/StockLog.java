package com.fenikskrylo.dechallintier.feniksystem.domain.stock;

import com.fenikskrylo.dechallintier.feniksystem.domain.NewTimeEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class StockLog extends NewTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    @Column
    private int inStock;

    @Column
    private int stockAdd;

    @Column
    private int stockSub;

    @Column
    private long barcodeId;

    @Column
    private String name;
}
