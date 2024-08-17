package com.tinqinacademy.hotelbff.rest.configuration;

import com.tinqinacademy.hotelbff.api.exceptions.HotelBffException;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.rest.security.JwtAuthenticationEntryPoint;
import com.tinqinacademy.hotelbff.rest.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
            RestApiRoutes.UNBOOK_ROOM,
            RestApiRoutes.GET_BOOKING_HISTORY
    };

    private final String[] ADMIN_ONLY_URLS = {
            RestApiRoutes.REGISTER_GUEST,
            RestApiRoutes.GET_REPORT,
            RestApiRoutes.CREATE_ROOM,
            RestApiRoutes.UPDATE_ROOM,
            RestApiRoutes.UPDATE_PARTIALLY_ROOM,
            RestApiRoutes.DELETE_ROOM,
            RestApiRoutes.GET_ALL_USERS_BY_PARTIAL_NAME,
            RestApiRoutes.UPDATE_PARTIALLY_BOOKING
    };

    @Bean
    public UserDetailsService emptyDetailsService() {
        // This will stop spring security to create a password from console
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

    private void secureAdminEndpoints(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, RestApiRoutes.REGISTER_GUEST).hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, RestApiRoutes.GET_REPORT).hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, RestApiRoutes.CREATE_ROOM).hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, RestApiRoutes.UPDATE_ROOM).hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, RestApiRoutes.UPDATE_PARTIALLY_ROOM).hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, RestApiRoutes.DELETE_ROOM).hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, RestApiRoutes.UPDATE_PARTIALLY_BOOKING).hasRole("ADMIN")
        );
    }

    private void secureUserEndpoints(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers(HttpMethod.POST, RestApiRoutes.BOOK_ROOM).hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, RestApiRoutes.UNBOOK_ROOM).hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.GET, RestApiRoutes.AUTH_CHECK_JWT).hasAnyRole("USER", "ADMIN")
                //.requestMatchers(HttpMethod.PATCH, RestApiRoutes.GET_BOOKING_HISTORY).hasAnyRole("USER", "ADMIN")
        );
    }
}
