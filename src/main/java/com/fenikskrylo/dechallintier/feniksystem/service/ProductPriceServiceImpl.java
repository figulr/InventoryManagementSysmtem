package com.fenikskrylo.dechallintier.feniksystem.service;

import com.fenikskrylo.dechallintier.feniksystem.domain.price.PriceLog;
import com.fenikskrylo.dechallintier.feniksystem.domain.price.PriceLogRepository;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductPriceResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductPriceUpdateDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductPriceServiceImpl implements ProductPriceService{

    private final PriceLogRepository priceLogRepository;

    @Override
    public Long save(ProductPriceUpdateDto dto) {
        System.out.println("save 시작");
        PriceLog priceLog = PriceLog.builder()
                .barcodeId(dto.getBarcodeId())
                .updatedPrice(dto.getUpdatedPrice())
                .name(dto.getName())
                .build();
        priceLogRepository.save(priceLog);
        System.out.println("save 완료");
        return priceLog.getPriceId();
    }

    @Override
    public boolean checkHistory(long barcode) {
        Optional<PriceLog> optionalPriceLog = priceLogRepository.findByBarcodeId(barcode);
        if(!optionalPriceLog.isPresent()){
            return false;
        }
        return true;
    }

    @Override
    public List<ProductPriceResponseDto> latestLog(long barcode) {
        List<ProductPriceResponseDto> priceHistory
                = new ArrayList<>();
        Optional<List<PriceLog>> optionalProductPriceResponseDtoList =
                priceLogRepository.findTop5ByBarcodeIdOrderByCreatedDateDesc(barcode);
        if(!optionalProductPriceResponseDtoList.isPresent()){
            boolean result = false;
            ProductPriceResponseDto dto = new ProductPriceResponseDto(result);
            priceHistory.add(dto);
            return priceHistory;
        }
        List<PriceLog> priceLogList = optionalProductPriceResponseDtoList.get();
        return priceLogList.stream().map(ProductPriceResponseDto::new).collect(Collectors.toList());
    }
}
