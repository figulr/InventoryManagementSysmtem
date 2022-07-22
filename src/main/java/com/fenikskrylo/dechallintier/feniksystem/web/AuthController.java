package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fenikskrylo.dechallintier.feniksystem.service.MemberService;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.UserFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
public class AuthController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/auth/register")
    public String userRegister(Model model){
        model.addAttribute("userFormDto", new UserFormDto());
        return "auth/register";
    }

    @RequestMapping("/auth/login")
    public String login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication.isAuthenticated()){
            return "auth/login";
        } else {
            return "redirect:/";
        }

    }


}
