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

@RequiredArgsConstructor
@RestController
public class ProductsAPIController {
    private final ProductsService productsService;
    private final ProductStockService productStockService;
    private final ProductPriceService productPriceService;

    @ResponseBody
    @GetMapping("/api/v1/register/check/{barcode}")
    public boolean check(@PathVariable long barcode){
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
    public Long stockSave(@RequestBody ProductStockUpdateDto stockDto){
        return productStockService.save(stockDto);
    }

    @ResponseBody
    @GetMapping("/api/v1/in/{barcode}")
    public ProductsResponseDto intoList(@PathVariable Long barcode){
        System.out.println("진입");
        ProductsResponseDto products = productsService.findByBarcodeId(barcode);
        System.out.println(products.getBarcodeId());
        return products;
    }

    // price manage
    // @ResponseBody
    @PostMapping("/api/v1/price/{barcode}")
    public Long priceUpdate(@RequestBody ProductPriceUpdateDto priceDto){
        return productsService.priceUpdate(priceDto.getId(), priceDto.getUpdatedPrice(), priceDto.getName(), priceDto);
    }


}


