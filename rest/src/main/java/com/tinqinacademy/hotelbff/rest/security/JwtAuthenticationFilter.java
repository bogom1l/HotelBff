package com.tinqinacademy.hotelbff.rest.security;

import com.tinqinacademy.authentication.api.operations.validatejwt.ValidateJwtOutput;
import com.tinqinacademy.hotelbff.domain.AuthRestClient;
import com.tinqinacademy.hotelbff.rest.configuration.UserContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * JwtAuthenticationFilter intercepts all incoming requests (OncePerRequestFilter).
 * It checks for the presence and format of the JWT token in the Authorization header.
 * If a valid token is found, it calls the AuthRestClient (from the Authentication microservice) to validate the token.
 * It retrieves the token itself using the getToken method.
 * If the token is valid, it decodes the payload using the jwtDecoder.
 * It extracts user information (ID and role) from the decoded payload.
 * It creates a custom CustomAuthenticationToken object with extracted user details and a SimpleGrantedAuthority based on the role.
 * It sets the user context in the SecurityContextHolder with the created token.
 * This makes user information accessible throughout the request lifecycle for authorization checks within controllers or other components.
 * If any error occurs during processing, the request continues without authentication.

 * Overall, this filter ensures that only requests with valid JWT tokens have access to the Hotel BFF application endpoints.
 */

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtDecoder jwtDecoder;
    private final AuthRestClient authRestClient;
    private final UserContext userContext;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION); //The "Authorization" header contains the JWT token

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        ValidateJwtOutput validateJwtOutput = authRestClient.validateJwt(authHeader);

        String token = getToken(request);

        if (token != null && !token.isEmpty() && validateJwtOutput.getIsValid()) {
            try {
                Map<String, Object> payloadMap = jwtDecoder.getPayloadFromJwt(token);

                String userId = (String) payloadMap.get("sub");
                String role = (String) payloadMap.get("role");
                GrantedAuthority authority = new SimpleGrantedAuthority(role);

                CustomAuthenticationToken customAuthenticationToken = new CustomAuthenticationToken(
                        List.of(authority), userId
                );
                customAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(customAuthenticationToken);
                userContext.setUserId(userId);
            } catch (Exception e) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
