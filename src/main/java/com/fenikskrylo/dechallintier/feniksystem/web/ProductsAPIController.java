package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fenikskrylo.dechallintier.feniksystem.config.auth.LoginUser;
import com.fenikskrylo.dechallintier.feniksystem.config.auth.dto.SessionUser;
import com.fenikskrylo.dechallintier.feniksystem.domain.product.Products;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductPriceService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductStockService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductsService;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductsAPIController {
    private final ProductsService productsService;
    private final ProductStockService productStockService;
    private final ProductPriceService productPriceService;

    @ResponseBody
    @GetMapping("/api/v1/register/check/{barcode}")
    public boolean check(@PathVariable long barcode){
        System.out.println("api 도착");
        System.out.println(barcode);
        return productsService.check(barcode);
    }
    // url 모두 동일해도 Mapping 방식이 다르다면 괜찮다.
    @PostMapping("/api/v1/register")
    public boolean register(@RequestBody ProductsSaveRequestDto requestDto){
        return productsService.save(requestDto);
    }

    @PutMapping("/api/v1/update/{id}")
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
    // @ResponseBody
    @PostMapping("/api/v1/price/{barcode}")
    public Long priceUpdate(@RequestBody ProductPriceUpdateDto priceDto, @LoginUser SessionUser user){

        return productsService.priceUpdate(priceDto.getId(), priceDto.getUpdatedPrice(), user, priceDto);
//        priceDto.setName(user.getName());
//        return productPriceService.save(priceDto);
    }


}


