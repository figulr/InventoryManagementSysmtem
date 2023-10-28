package com.fenikskrylo.dechallintier.feniksystem.service;

import com.fenikskrylo.dechallintier.feniksystem.domain.member.Member;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface MemberService extends UserDetailsService {

    boolean saveMember(Member member);

    boolean emailCheck(String email);

    String getName(String email);


}
