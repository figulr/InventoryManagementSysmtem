package com.fenikskrylo.dechallintier.feniksystem.config.auth.dto;

import com.fenikskrylo.dechallintier.feniksystem.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
