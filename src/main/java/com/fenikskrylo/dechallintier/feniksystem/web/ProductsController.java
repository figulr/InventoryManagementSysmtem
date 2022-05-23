package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fenikskrylo.dechallintier.feniksystem.domain.PurchaseAt;
import com.fenikskrylo.dechallintier.feniksystem.domain.Unit;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductPriceService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductStockService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductsService;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.EnumSet;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductsController {

    private final ProductsService productsService;
    private final ProductStockService productStockService;
    private final ProductPriceService productPriceService;

    @GetMapping("/product/register")
    public String productRegister(Model model){
        EnumSet<PurchaseAt> list = EnumSet.allOf(PurchaseAt.class);
        EnumSet<Unit> unit = EnumSet.allOf(Unit.class);
        model.addAttribute("list", list);
        model.addAttribute("unit", unit);
        return "/product/register";
    }

    @GetMapping("/product/detail/{barcode}")
    public String productDetail(Model model, @PathVariable long barcode){
        ProductsResponseDto dto = productsService.findByBarcodeId(barcode);
        ProductStockResponseDto stockDto = productStockService.latestLog(barcode);
//        List<ProductPriceResponseDto> priceDto = productPriceService.latestLog(barcode);
        model.addAttribute("product",dto);
        model.addAttribute("stock", stockDto);
//        model.addAttribute("priceList", priceDto);
        return "/product/detail";

    }

    @GetMapping("/product/search")
    public String productSearch(){
        return "/product/seasrch";
    }

    @GetMapping("/product/list")
    public String productList(Model model){
        model.addAttribute("list", productsService.findAllDesc());
        return "/product/list";
    }

    @GetMapping("/product/update/{id}")
    public String productEdit(@PathVariable Long id, Model model){
        ProductsResponseDto dto = productsService.findById(id);
        System.out.println(dto);
        model.addAttribute("product", dto);
        return "/product/update";
    }
}
