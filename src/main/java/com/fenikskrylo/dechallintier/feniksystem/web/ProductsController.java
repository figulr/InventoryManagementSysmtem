package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fenikskrylo.dechallintier.feniksystem.domain.PurchaseAt;
import com.fenikskrylo.dechallintier.feniksystem.domain.Unit;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductPriceService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductStockService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductsService;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductPriceResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsSearchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        System.out.println(dto);
        ProductStockResponseDto stockDto = productStockService.latestLog(barcode);
        if(productPriceService.checkHistory(barcode)){
         List<ProductPriceResponseDto> priceDto = productPriceService.latestLog(barcode);
         model.addAttribute("priceList", priceDto);}
        else {
            ProductPriceResponseDto nonePriceDto =
                    new ProductPriceResponseDto(productPriceService.checkHistory(barcode));
            List<ProductPriceResponseDto> nonePirceList = new ArrayList<>();
            nonePirceList.add(nonePriceDto);
            model.addAttribute("priceList", nonePirceList);
        };
        model.addAttribute("product",dto);
        model.addAttribute("stock", stockDto);

        return "/product/detail";

    }

    @GetMapping("/product/search")
    public String searchPage(){
        return "/product/search";
    }

    // search manage
//    @ResponseBody
    @GetMapping("/product/search.api")
    public String productSearch(ProductsSearchRequestDto dto, Model model){
        System.out.println(">>>>>> controller 진입");
        System.out.println(dto);
        List<ProductsResponseDto> productsList = new ArrayList<>();
        switch (dto.getSearchType()){
            case "productName":
                System.out.println("함수진입1");
                productsList = productsService.searchProductName(dto.getSearchValue());
                System.out.println(productsList);
                break;
            case "brand":
                System.out.println("함수진입2");
                productsList = productsService.searchBrand(dto.getSearchValue());
                System.out.println(productsList);
                break;
        }
        System.out.println(productsList);
        System.out.println(">>>>>> controller 끝");
        model.addAttribute("list", productsList);
        return "/product/search";
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
