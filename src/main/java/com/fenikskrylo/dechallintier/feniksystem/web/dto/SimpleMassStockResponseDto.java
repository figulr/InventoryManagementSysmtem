package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class SimpleMassStockResponseDto {
    List<Long> list;
    boolean result;

    public SimpleMassStockResponseDto(boolean result) {
        this.result = result;
    }

    public SimpleMassStockResponseDto(List<Long> list, boolean result) {
        this.list = list;
        this.result = result;
    }
}

