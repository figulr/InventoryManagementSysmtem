package com.fenikskrylo.dechallintier.feniksystem.service;

import com.fenikskrylo.dechallintier.feniksystem.domain.stock.StockLog;
import com.fenikskrylo.dechallintier.feniksystem.domain.stock.StockLogRepository;
import com.fenikskrylo.dechallintier.feniksystem.domain.user.User;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ProductStockServiceImpl implements ProductStockService {
    private final StockLogRepository stockLogRepository;

//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    User userDetails = (UserDetails)principal;

//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Override
    public Long save(ProductStockUpdateDto dto) {
        StockLog stockLog = new StockLog();
        stockLog.setInStock(dto.getInStock());
        stockLog.setStockAdd(dto.getStockAdd());
        stockLog.setStockSub(dto.getStockSub());
        stockLog.setName(dto.getName());
        stockLog.setBarcodeId(dto.getBarcodeId());
        return stockLogRepository.save(stockLog).getStockId();
    }

    @Override
    public ProductStockResponseDto latestLog(long barcode) {
        Optional<StockLog> optionalStockLog = stockLogRepository.findFirstByBarcodeIdOrderByCreatedDateDesc(barcode);
        if(!optionalStockLog.isPresent()){
            ProductStockResponseDto dto =ProductStockResponseDto.builder().inStock(0).barcodeId(barcode).build();
            return dto;
        }
        StockLog stockLog = optionalStockLog.get();
        ProductStockResponseDto dto =
                ProductStockResponseDto.builder().inStock(stockLog.getInStock()).barcodeId(stockLog.getBarcodeId()).build();
        return dto;
    }
}
