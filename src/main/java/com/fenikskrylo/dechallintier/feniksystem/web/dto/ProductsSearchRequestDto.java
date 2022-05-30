package com.fenikskrylo.dechallintier.feniksystem.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductsSearchRequestDto {
    private String searchType;
    private String searchValue;

   /* public ProductsSearchRequestDto(String searchType, String searchValue){
        System.out.println("dto 진입");
        this.searchType = searchType;
        this.searchValue = searchValue;
        System.out.println("dto 탈출");
    }*/

}
