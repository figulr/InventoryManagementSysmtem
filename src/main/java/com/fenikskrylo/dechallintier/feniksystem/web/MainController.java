package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fenikskrylo.dechallintier.feniksystem.config.auth.LoginUser;
import com.fenikskrylo.dechallintier.feniksystem.config.auth.dto.SessionUser;
import com.fenikskrylo.dechallintier.feniksystem.service.ProductsService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){

        if(user!=null){
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }
}
