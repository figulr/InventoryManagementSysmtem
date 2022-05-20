package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fenikskrylo.dechallintier.feniksystem.config.auth.LoginUser;
import com.fenikskrylo.dechallintier.feniksystem.config.auth.dto.SessionUser;
import com.fenikskrylo.dechallintier.feniksystem.domain.user.User;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductStockService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductsService;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockUpdateDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsSaveRequestDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProductsAPIController {
    private final ProductsService productsService;
    private final ProductStockService productStockService;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

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
        System.out.println(">>>>>>>>>>>>api 진입");
//        User principal = (User)authentication.getPrincipal();
//        System.out.println(">>>>>>>>>>>>유저정보 : "+principal.getName());
//        stockDto.setName(principal.getName());
//        stockDto.setName(authentication.getName());
        stockDto.setName(user.getName());
        System.out.println(user.getName());
        return productStockService.save(stockDto);
    }

}
