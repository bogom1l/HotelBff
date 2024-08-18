package com.tinqinacademy.hotelbff.rest.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * This class implements the AuthenticationEntryPoint interface from Spring Security.
 * It's responsible for handling situations where a user attempts to access a protected resource without
 * providing valid authentication credentials (in our case, a JWT token).
 * for example - handle when token is invalid or expired
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
}
