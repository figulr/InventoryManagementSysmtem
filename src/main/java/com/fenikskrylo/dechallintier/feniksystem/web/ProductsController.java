package com.fenikskrylo.dechallintier.feniksystem.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ProductsController {

    @GetMapping("/product/register")
    public String productRegister(){
        return "/product/register";
    }
}
