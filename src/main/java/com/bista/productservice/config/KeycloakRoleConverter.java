package com.bista.productservice.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Map<String, List<String>> realmClaims = (Map<String, List<String>>) jwt.getClaims().get("realm_access");
        if(realmClaims == null || realmClaims.isEmpty()) {
            return new ArrayList<>();
        }
        Collection<GrantedAuthority> authorities  = realmClaims.get("roles").stream()
                .map(role -> new SimpleGrantedAuthority("ROLE" + role)).collect(Collectors.toSet());
        return authorities;
    }
}
