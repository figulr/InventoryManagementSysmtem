package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fenikskrylo.dechallintier.feniksystem.config.auth.LoginUser;
import com.fenikskrylo.dechallintier.feniksystem.config.auth.dto.SessionUser;
import com.fenikskrylo.dechallintier.feniksystem.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final MemberService memberService;

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User member = (User) authentication.getPrincipal();
        String userName = memberService.getName(member.getUsername());
        if (member != null) {
            model.addAttribute("userName", userName);
        }
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
