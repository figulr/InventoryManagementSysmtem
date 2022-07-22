package com.fenikskrylo.dechallintier.feniksystem.domain.member;

import com.fenikskrylo.dechallintier.feniksystem.domain.BaseTimeEntity;
import com.fenikskrylo.dechallintier.feniksystem.web.dto.UserFormDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter @Setter
@Table(name = "member")
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;


    public static Member createUser(UserFormDto userFormDto,PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setEmail(userFormDto.getEmail());
        member.setName(userFormDto.getName());
//        String password = passwordEncoder.encode(userFormDto.getPassword());
        String password = BCrypt.hashpw(userFormDto.getPassword(), BCrypt.gensalt());
        member.setPassword(password);
        member.setRole(Role.GUEST);
        return member;
    }

    @Builder
    public Member(String name, String email, Role role){
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Member update(String name){
        this.name = name;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
