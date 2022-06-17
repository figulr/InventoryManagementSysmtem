package com.fenikskrylo.dechallintier.feniksystem.domain.stock;

import com.fenikskrylo.dechallintier.feniksystem.domain.NewTimeEntity;
import com.fenikskrylo.dechallintier.feniksystem.domain.product.Products;
import com.fenikskrylo.dechallintier.feniksystem.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
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
