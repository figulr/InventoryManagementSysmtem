package com.fenikskrylo.dechallintier.feniksystem.service;

import com.fenikskrylo.dechallintier.feniksystem.domain.product.Products;
import com.fenikskrylo.dechallintier.feniksystem.domain.product.ProductsRepository;
import com.fenikskrylo.dechallintier.feniksystem.domain.stock.StockLog;
import com.fenikskrylo.dechallintier.feniksystem.domain.stock.StockLogRepository;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductStockServiceImpl implements ProductStockService {
    private final StockLogRepository stockLogRepository;
    private final ProductsRepository productsRepository;

    private final EntityManager em;

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
        if (!optionalStockLog.isPresent()) {
            ProductStockResponseDto dto = ProductStockResponseDto.builder().inStock(0).barcodeId(barcode).build();
            return dto;
        }
        StockLog stockLog = optionalStockLog.get();
        ProductStockResponseDto dto =
                ProductStockResponseDto.builder().inStock(stockLog.getInStock()).barcodeId(stockLog.getBarcodeId()).build();
        return dto;
    }

    @Override
    public List<ProductStockResponseDto> stockList() {
        // int zero = 0;
        Optional<List<StockLog>> optionalStockLog =
                stockLogRepository.find();
        if (!optionalStockLog.isPresent()) {
            return Collections.emptyList();
        }
        List<StockLog> stockLog = optionalStockLog.get();
        List<ProductStockResponseDto> responseDtos = new ArrayList<>();
        List<Long> _barcodeList = new ArrayList<>();
        List<LocalDateTime> _createdDate = new ArrayList<>();
        for (StockLog stock : stockLog) {
            Optional<Products> optionalProducts = productsRepository.findByBarcodeId(stock.getBarcodeId());
            if (!optionalProducts.isPresent()) {
                System.out.println("이 상품은 검색이 안된다." + stock.getBarcodeId());
            } else {
                // 중복 체크
                long barcodeId = stock.getBarcodeId();
                LocalDateTime createdDate = stock.getCreatedDate();
                // 이미 바코드가 있으면
                if (_barcodeList.contains(barcodeId)) {
                    int j = _barcodeList.indexOf(barcodeId);
                    // 이미 등록된 바코드가 예전거라면
                    if (_createdDate.get(j).isBefore(createdDate)) {
                        // 날짜 지금걸로 새롭게 세팅
                        _createdDate.set(j, createdDate);
                        // 저장
                        Products products = optionalProducts.get();
                        String productName = products.getProductName();
                        String brand = products.getBrand();
                        ProductStockResponseDto dto = new ProductStockResponseDto(stock, productName, brand);
                        responseDtos.set(j, dto);
                    } else {
                        // 아니면 이번 건 패스
                    }
                } else {
                    // 새로운 바코드와 새로운 날짜 추가
                    _barcodeList.add(barcodeId);
                    _createdDate.add(createdDate);
                    // 저장
                    Products products = optionalProducts.get();
                    String productName = products.getProductName();
                    String brand = products.getBrand();
                    ProductStockResponseDto dto = new ProductStockResponseDto(stock, productName, brand);
                    responseDtos.add(dto);
                }
            }
        }
        System.out.println(responseDtos);
        return responseDtos;
    }

    @Override
    public List<ProductStockResponseDto> dailyStockLog(LocalDate date) {
        Optional<List<StockLog>> optionalList =
                stockLogRepository.findByCreatedDateBetweenOrderByCreatedDateDesc(date.atStartOfDay(),
                        date.atStartOfDay().plusDays(1));

        if (!optionalList.isPresent()) {
            return Collections.emptyList();
        }
        List<StockLog> stockLog = optionalList.get();
        List<ProductStockResponseDto> responseDtos = new ArrayList<>();

        for (StockLog stock : stockLog) {
            Optional<Products> optionalProducts = productsRepository.findByBarcodeId(stock.getBarcodeId());
            if (!optionalProducts.isPresent()) {
                System.out.println("이 상품은 검색이 안된다." + stock.getBarcodeId());
            } else {
                // 저장
                Products products = optionalProducts.get();
                String productName = products.getProductName();
                String brand = products.getBrand();
                long price = products.getPrice();
                ProductStockResponseDto dto = new ProductStockResponseDto(stock, productName, brand, price);
                responseDtos.add(dto);
            }
        }
        return responseDtos;

    }
}
