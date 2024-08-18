package com.tinqinacademy.hotelbff.rest.configuration;

import com.tinqinacademy.hotelbff.api.exceptions.HotelBffException;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.rest.security.JwtAuthenticationEntryPoint;
import com.tinqinacademy.hotelbff.rest.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is annotated with @Configuration and @EnableWebSecurity,
 * making it a Spring configuration bean and enabling Spring Security for your application.
 * It injects the custom JwtAuthenticationFilter and JwtAuthenticationEntryPoint beans for further configuration.
 * <p>
 * A custom UserDetailsService bean (emptyDetailsService) is defined, preventing Spring Security from
 * attempting to create local users (as we are relying only on JWT tokens).
 * <p>
 * <p>
 * Disables CSRF protection as it's not typically required for JWT-based authentication.
 * <p>
 * Defines authorization rules for different URL patterns using lambdas.
 * <p>
 * Adds the jwtAuthenticationFilter before the default UsernamePasswordAuthenticationFilter.
 * This ensures JWT token processing happens before attempting other authentication methods.
 * <p>
 * Sets the jwtAuthenticationEntryPoint bean as the default handler for authentication failures.
 * <p>
 * Disables session management as your application relies on stateless JWT tokens.
 * <p>
 * <p>
 * Overall, this configuration establishes a security framework that leverages JWT tokens for authentication and authorization.
 * It defines access rules for different URL patterns based on user roles
 * and disables unnecessary features like session management and CSRF protection.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    private final String[] PUBLIC_URLS = {
            RestApiRoutes.GET_ROOM_INFO,
            RestApiRoutes.CHECK_ROOM_AVAILABILITY
    };

    private final String[] USER_ONLY_URLS = {
            RestApiRoutes.AUTH_CHECK_JWT,
            RestApiRoutes.BOOK_ROOM,
            RestApiRoutes.UNBOOK_ROOM
    };

    private final String[] ADMIN_ONLY_URLS = {
            RestApiRoutes.REGISTER_GUEST,
            RestApiRoutes.GET_REPORT,
            RestApiRoutes.CREATE_ROOM,
            RestApiRoutes.UPDATE_ROOM,
            RestApiRoutes.UPDATE_PARTIALLY_ROOM,
            RestApiRoutes.DELETE_ROOM,
            RestApiRoutes.UPDATE_PARTIALLY_BOOKING,
            RestApiRoutes.GET_BOOKING_HISTORY
    };

    /**
     *  Prevent Spring Security from attempting to create or manage local user accounts,
     *   as the application relies solely on JWT tokens for authentication.

     *  This stops Spring Security to create the default password from console.
     */
    @Bean
    public UserDetailsService emptyDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                throw new HotelBffException("No local users, only JWT tokens allowed.", HttpStatus.NOT_FOUND);
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers(PUBLIC_URLS).permitAll();
                    authorize.requestMatchers(ADMIN_ONLY_URLS).hasAuthority("ADMIN");
                    authorize.requestMatchers(USER_ONLY_URLS).hasAnyAuthority("USER", "ADMIN");
                    authorize.anyRequest().permitAll();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
