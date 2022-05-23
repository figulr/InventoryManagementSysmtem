package com.fenikskrylo.dechallintier.feniksystem.service;

import com.fenikskrylo.dechallintier.feniksystem.domain.product.Products;
import com.fenikskrylo.dechallintier.feniksystem.domain.product.ProductsRepository;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsListResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsSaveRequestDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    @Transactional
    public Long save(ProductsSaveRequestDto requestDto){
        return productsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, ProductsUpdateRequestDto requestDto){
        Products products =
                productsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("제품이 " +
                "존재하지 않습니다. id = "+id));
        products.update(requestDto.getProductName(), requestDto.getPrice(), requestDto.getWeight(),
                requestDto.getUnit(),
                requestDto.getVolumeLong(),
                requestDto.getVolumeShort(), requestDto.getVolumeHeight());
        return id;
    }

    // Price Update
    @Transactional
    public Long priceUpdate(Long id, long price){
        Products product = productsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("제품이 존재하지 " +
                "않습니다."));
        System.out.println(">>>>>>>>"+product);
        product.update(price);
        System.out.println("업데이트 완료");
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
            new IllegalArgumentException("존재하지 않는 바코드입니다.");
        }
        Products entity = optionalEntity.get();
        return new ProductsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<ProductsListResponseDto> findAllDesc(){
        return productsRepository.findAllDesc().stream()
                .map(ProductsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
