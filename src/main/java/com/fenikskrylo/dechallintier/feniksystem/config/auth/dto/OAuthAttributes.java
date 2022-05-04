package com.fenikskrylo.dechallintier.feniksystem.config.auth.dto;

import com.fenikskrylo.dechallintier.feniksystem.domain.user.Role;
import com.fenikskrylo.dechallintier.feniksystem.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email){
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName,
                                     Map<String, Object> attributes) {
        if("naver".equals(registrationId)){
            return ofNaver("id", attributes);
        }
        return null;
    }

    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public com.fenikskrylo.dechallintier.feniksystem.domain.user.User toEntity(){
        return User.builder()
                .name(name)
                .email(email)
                .role(Role.GUEST)
                .build();
    }
}
