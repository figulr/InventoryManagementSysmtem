package com.fenikskrylo.dechallintier.feniksystem.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GEST", "손님"),
    USER("ROLE_USER", "관리자");

    private final String key;
    private final String title;
}
