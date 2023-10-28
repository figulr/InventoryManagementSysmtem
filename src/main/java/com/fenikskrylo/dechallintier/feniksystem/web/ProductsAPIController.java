package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fenikskrylo.dechallintier.feniksystem.config.auth.LoginUser;
import com.fenikskrylo.dechallintier.feniksystem.config.auth.dto.SessionUser;
import com.fenikskrylo.dechallintier.feniksystem.domain.product.Products;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductPriceService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductStockService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductsService;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ProductsAPIController {
    private final ProductsService productsService;
    private final ProductStockService productStockService;
    private final ProductPriceService productPriceService;

    @ResponseBody
    @GetMapping("/api/v1/register/check/{barcode}")
    public boolean check(@PathVariable long barcode) {
        return productsService.check(barcode);
    }

    // url 모두 동일해도 Mapping 방식이 다르다면 괜찮다.
    @PostMapping("/api/v1/register")
    public boolean register(@RequestBody ProductsSaveRequestDto requestDto) {
        return productsService.save(requestDto);
    }

    @PutMapping("/api/v1/update/{id}")
    public Long update(@PathVariable Long id, @RequestBody ProductsUpdateRequestDto requestDto) {
        return productsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/delete/{id}")
    public Long delete(@PathVariable Long id) {
        productsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/product/{id}")
    public ProductsResponseDto findById(@PathVariable Long id) {
        return productsService.findById(id);
    }


    // stock manage
    @PostMapping("/api/v1/stock/{barcode}")
    public Long stockSave(@RequestBody ProductStockUpdateDto stockDto) {
        return productStockService.save(stockDto);
    }

    // in-out 조회
    @ResponseBody
    @GetMapping("/api/v1/stock/check/{barcode}")
    public ProductsResponseDto intoList(@PathVariable Long barcode) {
        ProductsResponseDto productDto = productsService.findByBarcodeId(barcode);
        Products product = Products.builder()
                .id(productDto.getId())
                .barcodeId(productDto.getBarcodeId())
                .productName(productDto.getProductName())
                .brand(productDto.getBrand())
                .price(productDto.getPrice())
                .store(productDto.getStore())
                .weight(productDto.getWeight())
                .unit(productDto.getUnit())
                .volumeHeight(productDto.getVolumeHeight())
                .volumeLong(productDto.getVolumeLong())
                .volumeShort(productDto.getVolumeShort())
                .build();
        int inStock = productStockService.latestLog(barcode).getInStock();
        System.out.println(inStock);
        ProductsResponseDto response = new ProductsResponseDto(product, inStock);
        return response;
    }

    // into stock
    @ResponseBody
    @PostMapping("/api/v1/stock/in")
    public SimpleMassStockResponseDto stockIn(@RequestBody String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> list =
                mapper.readValue(data, new TypeReference<List<Map<String, String>>>() {
                });
        ArrayList<Long> barcodeList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            long barcode = Long.parseLong(list.get(i).get("barcodeId"));
            int inStock = Integer.parseInt(list.get(i).get("inStock"));
            int stockAdd = Integer.parseInt(list.get(i).get("stockAdd"));
            int stockSub = Integer.parseInt(list.get(i).get("stockSub"));
            String name = list.get(i).get("name");
            System.out.println(barcode + inStock + stockAdd + stockSub + name);
            ProductStockUpdateDto stockDto = ProductStockUpdateDto.builder()
                    .barcodeId(barcode)
                    .inStock(inStock)
                    .stockAdd(stockAdd)
                    .stockSub(stockSub)
                    .name(name)
                    .build();
            if (productStockService.save(stockDto) == 0L) {
                barcodeList.add(barcode);
            }
        }
        if (barcodeList.isEmpty()) {
            return new SimpleMassStockResponseDto(true);
        } else {
            return new SimpleMassStockResponseDto(barcodeList, false);
        }
    }

    // out from stock

    // price manage
    // @ResponseBody
    @PostMapping("/api/v1/price/{barcode}")
    public Long priceUpdate(@RequestBody ProductPriceUpdateDto priceDto) {
        return productsService.priceUpdate(priceDto.getId(), priceDto.getUpdatedPrice(), priceDto.getName(), priceDto);
    }


}


