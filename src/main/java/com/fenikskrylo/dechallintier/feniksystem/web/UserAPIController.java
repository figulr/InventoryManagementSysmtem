package com.fenikskrylo.dechallintier.feniksystem.web;

import com.fenikskrylo.dechallintier.feniksystem.domain.member.Member;
import com.fenikskrylo.dechallintier.feniksystem.service.MemberService;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.UserFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserAPIController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/api/v0/user/check/{email}")
    public boolean emailCheck(@PathVariable String email){
        boolean result = memberService.emailCheck(email);
        return result;
    }

    @PostMapping("/api/v0/user/register")
    public boolean userRegisterSubmit(@RequestBody UserFormDto dto){
        System.out.println("진입");
        Member member = Member.createUser(dto, passwordEncoder);
//        Member member = Member.createUser(dto);
        boolean result = memberService.saveMember(member);
        System.out.println(result);
        System.out.println("완료");
        return result;
    }
}