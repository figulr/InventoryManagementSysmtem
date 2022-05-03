package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fenikskrylo.dechallintier.feniksystem.service.ProductsService;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsListResponseDto;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.ProductsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class ProductsController {

    private final ProductsService productsService;

    @GetMapping("/product/register")
    public String productRegister(){
        return "/product/register";
    }

    @GetMapping("/product/list")
    public String productList(Model model){
        model.addAttribute("list", productsService.findAllDesc());
        return "/product/list";
    }

    @GetMapping("/product/update/{id}")
    public String productEdit(@PathVariable Long id, Model model){
        ProductsResponseDto dto = productsService.findById(id);
        model.addAttribute("product", dto);
        return "/product/update";
    }
}
