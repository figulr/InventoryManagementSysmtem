package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fenikskrylo.dechallintier.feniksystem.config.auth.dto.SessionUser;
import com.fenikskrylo.dechallintier.feniksystem.domain.PurchaseAt;
import com.fenikskrylo.dechallintier.feniksystem.domain.Unit;
import com.fenikskrylo.dechallintier.feniksystem.service.MemberService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductPriceService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductStockService;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductsService;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductPriceResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductStockResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsSearchRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ProductsController {

    private final ProductsService productsService;
    private final MemberService memberService;
    private final ProductStockService productStockService;
    private final ProductPriceService productPriceService;

    @GetMapping("/product/register")
    public String productRegister(Model model){
        EnumSet<PurchaseAt> list = EnumSet.allOf(PurchaseAt.class);
        EnumSet<Unit> unit = EnumSet.allOf(Unit.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User member = (User) authentication.getPrincipal();
        String userName = memberService.getName(member.getUsername());
        if(member != null){
            model.addAttribute("userName", userName);
        }
        model.addAttribute("list", list);
        model.addAttribute("unit", unit);
        return "product/register";
    }

    @GetMapping("/product/detail/{barcode}")
    public String productDetail(Model model, @PathVariable long barcode){
        ProductsResponseDto dto = productsService.findByBarcodeId(barcode);
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User member = (User) authentication.getPrincipal();
        String userName = memberService.getName(member.getUsername());
        if(member != null){
            model.addAttribute("userName", userName);
        }
        model.addAttribute("product",dto);
        model.addAttribute("stock", stockDto);
        return "product/detail";

    }

    @GetMapping("/product/search")
    public String searchPage(){
        return "product/search";
    }

    // search manage
    @GetMapping("/product/search.api")
    public String productSearch(ProductsSearchRequestDto dto, Model model){
        List<ProductsResponseDto> productsList = new ArrayList<>();
        switch (dto.getSearchType()){
            case "productName":
                productsList = productsService.searchProductName(dto.getSearchValue());
                break;
            case "brand":
                productsList = productsService.searchBrand(dto.getSearchValue());
                System.out.println(productsList);
                break;
        }
        System.out.println(productsList);
        model.addAttribute("list", productsList);
        return "product/search-result";
    }


    @GetMapping("/product/list")
    public String productList(Model model){
        model.addAttribute("list", productsService.findAllDesc());
        return "product/list";
    }

    // 재고리스트
    @GetMapping("/product/in-stock")
    public String productStockList(Model model){
        model.addAttribute("list", productStockService.stockList());
        return "product/in-stock";
    }

    @GetMapping("/product/update/{id}")
    public String productEdit(@PathVariable Long id, Model model){
        ProductsResponseDto dto = productsService.findById(id);
        System.out.println(dto);
        model.addAttribute("product", dto);
        return "product/update";
    }

    @GetMapping("/product/in-out/{mode}")
    public String productInOut(Model model, @PathVariable String mode){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User member = (User) authentication.getPrincipal();
        String userName = memberService.getName(member.getUsername());
        if(member != null){
            model.addAttribute("userName", userName);
        }
        model.addAttribute("mode", mode);
        return "product/in-out";
    }

    @GetMapping("/product/inout-result/{stringDate}")
    public String productInoutResult(Model model, @PathVariable String stringDate){
        List<ProductStockResponseDto> list;
        LocalDate date = LocalDate.parse(stringDate, DateTimeFormatter.ISO_DATE);
        if(date!=LocalDate.now()) {
            list = productStockService.dailyStockLog(date);
        } else{
            list = productStockService.dailyStockLog(LocalDate.now());
        }
        model.addAttribute("list", list);
        model.addAttribute("date", date);
        return "product/inout-result";
    }
}
