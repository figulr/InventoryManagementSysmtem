package com.fenikskrylo.dechallintier.feniksystem.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("GUEST", "손님"),
    USER("USER", "관리자");

    private final String key;
    private final String title;
}
