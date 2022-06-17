package com.fenikskrylo.dechallintier.feniksystem.domain.price;

import com.fenikskrylo.dechallintier.feniksystem.domain.NewTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PriceLog extends NewTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceId;

    @Column
    private long updatedPrice;

    @Column
    private long barcodeId;

    @Column
    private String name;
}
