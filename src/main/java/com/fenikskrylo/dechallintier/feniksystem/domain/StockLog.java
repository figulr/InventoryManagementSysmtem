package com.fenikskrylo.dechallintier.feniksystem.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class StockLog extends NewTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long stockId;



    @Column
    private Long countAdd;

    @Column
    private Long countSub;

}
