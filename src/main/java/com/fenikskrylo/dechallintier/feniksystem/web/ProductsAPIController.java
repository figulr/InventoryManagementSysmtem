package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fenikskrylo.dechallintier.feniksystem.service.ProductsService;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsSaveRequestDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ProductsAPIController {
    private final ProductsService productsService;

    @PostMapping("/api/v1/register")
    public Long register(@RequestBody ProductsSaveRequestDto requestDto){
        return productsService.save(requestDto);
    }

    @PutMapping("/api/v1/edit/{id}")
    public Long update(@PathVariable Long id, @RequestBody ProductsUpdateRequestDto requestDto){
        return productsService.update(id, requestDto);

    }

    @GetMapping("/api/v1/porduct/{id}")
    public ProductsResponseDto findById(@PathVariable Long id){
        return productsService.findById(id);
    }
}
