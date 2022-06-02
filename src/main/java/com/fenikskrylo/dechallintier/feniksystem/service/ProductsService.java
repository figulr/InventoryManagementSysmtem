package com.fenikskrylo.dechallintier.feniksystem.service;

import com.fenikskrylo.dechallintier.feniksystem.config.auth.LoginUser;
import com.fenikskrylo.dechallintier.feniksystem.config.auth.dto.SessionUser;
import com.fenikskrylo.dechallintier.feniksystem.domain.product.Products;
import com.fenikskrylo.dechallintier.feniksystem.domain.product.ProductsRepository;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductsService {
    private final ProductsRepository productsRepository;
    private final ProductPriceService productPriceService;

    @Transactional
    public boolean save(ProductsSaveRequestDto requestDto){
        long barcodeId = requestDto.getBarcodeId();;
        Optional<Products> optionalProducts = productsRepository.findByBarcodeId(barcodeId);
        if(optionalProducts.isPresent()){
            return false;
        }
        productsRepository.save(requestDto.toEntity());
        return true;
    }

    @Transactional
    public Long update(Long id, ProductsUpdateRequestDto requestDto){
        Products products =
                productsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("제품이 " +
                "존재하지 않습니다."));
        products.update(
                requestDto.getProductName(),
                requestDto.getWeight(),
                requestDto.getVolumeLong(),
                requestDto.getVolumeShort(),
                requestDto.getVolumeHeight());
        return products.getId();
    }

    // Price Update
    @Transactional
    public Long priceUpdate(Long id, long price, SessionUser user, ProductPriceUpdateDto priceDto){
        Products product = productsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("제품이 존재하지 " +
                "않습니다."));
        long presentPrice = product.getPrice();
        priceDto.setName(user.getName());
        priceDto.setUpdatedPrice(presentPrice);
        priceDto.setCreatedDate(product.getCreatedDate());
        productPriceService.save(priceDto);
        product.update(price);
        return id;
    }

    @Transactional
    public void delete(Long id){
        Products product = productsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("제품이 존재하지 않습니다. i d= "+id));
        productsRepository.delete(product);
    }

    public ProductsResponseDto findById(Long id){
        Products entity = productsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("제품이 존재하지 " +
                "않습니다. id = "+ id));
        return new ProductsResponseDto(entity);
    }

    public ProductsResponseDto findByBarcodeId(long barcodeId){
        Optional<Products> optionalEntity = productsRepository.findByBarcodeId(barcodeId);
        if(!optionalEntity.isPresent()){
            System.out.println("Optional 진입");
            boolean result = false;
            return new ProductsResponseDto(result);
        }else{
            Products entity = optionalEntity.get();
            return new ProductsResponseDto(entity);
        }
    }


    @Transactional(readOnly = true)
    public List<ProductsResponseDto> searchProductName(String productName){
        Optional<List<Products>> optionalProductsResponseDtoList =
                productsRepository.findByProductNameContaining(productName);
        if(!optionalProductsResponseDtoList.isPresent()){
            return Collections.emptyList();
        }
        List<Products> productsList = optionalProductsResponseDtoList.get();
        return productsList.stream().map(ProductsResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductsResponseDto> searchBrand(String brand){
        Optional<List<Products>> optionalProductsResponseDtoList =
                productsRepository.findByBrandContaining(brand);
        if(!optionalProductsResponseDtoList.isPresent()){
            return Collections.emptyList();
        }
        List<Products> productsList = optionalProductsResponseDtoList.get();
        System.out.println(productsList);
        return productsList.stream().map(ProductsResponseDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProductsListResponseDto> findAllDesc(){
        return productsRepository.findAllDesc().stream()
                .map(ProductsListResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public boolean check(long barcode) {
        System.out.println("check 진입");
        Optional<Products> optionalProducts = productsRepository.findByBarcodeId(barcode);
        if(optionalProducts.isPresent()){
            return false;
        }
        return true;
    }
}
