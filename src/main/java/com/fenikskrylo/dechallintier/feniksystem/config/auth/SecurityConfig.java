package com.fenikskrylo.dechallintier.feniksystem.config.auth;

import com.fenikskrylo.dechallintier.feniksystem.domain.member.Role;
import com.fenikskrylo.dechallintier.feniksystem.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final CustomOAuth2UserService customOAuth2UserService;

    private final MemberService memberService;

    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // h2 이용을 위해 추가
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile",
                        "/api/v0/**", "/auth/register")
                .permitAll()
                .antMatchers("/product/**", "/api/v1/**").hasRole(Role.GUEST.name())
//                .anyRequest().authenticated()
        ;

        http
                .formLogin()
                .loginPage("/auth/login")
                .failureHandler(getFailureHandler())
                .permitAll();
        http
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);
//
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService)

        super.configure(http);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService)
                .passwordEncoder(getPasswordEncoder());

        super.configure(auth);
    }
}
