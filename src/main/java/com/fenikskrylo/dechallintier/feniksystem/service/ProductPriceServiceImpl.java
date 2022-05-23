package com.fenikskrylo.dechallintier.feniksystem.service;

import com.fenikskrylo.dechallintier.feniksystem.domain.price.PriceLog;
import com.fenikskrylo.dechallintier.feniksystem.domain.price.PriceLogRepository;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductPriceResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductPriceUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

//    @Override
//    public List<ProductPriceResponseDto> latestLog(long barcode) {
//        List<ProductPriceResponseDto> priceHistory
//                = new ArrayList<>();
//        Optional<List<ProductPriceResponseDto>> optionalProductPriceResponseDtoList =
//                priceLogRepository.findFirstByBarcodeIdOrderByCreatedDateDesc(barcode);
//        if(!optionalProductPriceResponseDtoList.isPresent()){
//            ProductPriceResponseDto dto = ProductPriceResponseDto.builder()
//                    .history(false)
//                    .build();
//            priceHistory.add(dto);
//            return priceHistory;
//        }
//        priceHistory = optionalProductPriceResponseDtoList.get();
//        return priceHistory;
//    }
}
