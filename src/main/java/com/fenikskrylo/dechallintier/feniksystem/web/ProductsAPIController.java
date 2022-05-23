package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fenikskrylo.dechallintier.feniksystem.config.auth.LoginUser;
import com.fenikskrylo.dechallintier.feniksystem.config.auth.dto.SessionUser;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductPriceService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductStockService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductsService;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProductsAPIController {
    private final ProductsService productsService;
    private final ProductStockService productStockService;
    private final ProductPriceService productPriceService;

    // url 모두 동일해도 Mapping 방식이 다르다면 괜찮다.
    @PostMapping("/api/v1/register")
    public Long register(@RequestBody ProductsSaveRequestDto requestDto){
        return productsService.save(requestDto);
    }

    @PutMapping("/api/v1/edit/{id}")
    public Long update(@PathVariable Long id, @RequestBody ProductsUpdateRequestDto requestDto){
        return productsService.update(id, requestDto);}

    @DeleteMapping("/api/v1/delete/{id}")
    public Long delete(@PathVariable Long id){
        productsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/product/{id}")
    public ProductsResponseDto findById(@PathVariable Long id){
        return productsService.findById(id);
    }

    // stock manage
    @PostMapping("/api/v1/stock/{barcode}")
    public Long stockSave(@RequestBody ProductStockUpdateDto stockDto, @LoginUser SessionUser user){
        stockDto.setName(user.getName());
        return productStockService.save(stockDto);
    }

    // price manage
//    @ResponseBody
    @PostMapping("/api/v1/price/{barcode}")
    public Long priceUpdate(@RequestBody ProductPriceUpdateDto priceDto, @LoginUser SessionUser user){
        System.out.println("api 실행");
//        productsService.priceUpdate(priceDto.getId(), priceDto.getUpdatedPrice());
        priceDto.setName(user.getName());
        System.out.println("api 마지막 전");
        return productPriceService.save(priceDto);
    }
}


