package com.fenikskrylo.dechallintier.feniksystem.service;

import com.fenikskrylo.dechallintier.feniksystem.domain.member.Member;
import com.fenikskrylo.dechallintier.feniksystem.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public boolean saveMember(Member member) {
        validateDuplicateUser(member);
        memberRepository.save(member);
        return true;
    }

    private void validateDuplicateUser(Member member) {
        Member findUser = memberRepository.findByEmail(member.getEmail());
        if (findUser != null) {
            throw new IllegalStateException("이미 계정이 존재합니다.");
        }
    }

    @Override
    public boolean emailCheck(String email) {
        Member member = memberRepository.findByEmail(email);
        if (member != null) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username);
        if (member == null) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRoleKey())
                .build();
    }

    @Override
    public String getName(String email) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        return member.getName();
    }

}
