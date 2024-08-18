package com.tinqinacademy.hotelbff.rest.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * This class serves as a custom authentication token specifically designed for the Hotel BFF.
 * <p>
 * This custom token simplifies authentication flow in your Hotel BFF by
 * encapsulating user details and authorities required for Spring Security's authorization checks.
 * <p>
 * Holds user principal (user ID).
 * Stores a collection of GrantedAuthorities (user role).
 * Sets the token as authenticated by default because we only create the CustomAuthenticationToken after successful validation of the JWT.
 */
public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;

    public CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal) {
        super(authorities);
        this.principal = principal;
        setAuthenticated(true); // because we only create the CustomAuthenticationToken after successful validation of the JWT
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
