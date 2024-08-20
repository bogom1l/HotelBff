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
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> {
                    configureAdminEndpoints(authorize);
                    configureUserAndAdminEndpoints(authorize);
                    authorize.anyRequest().permitAll();
                })
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement((sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    private void configureAdminEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry request){
        request
                .requestMatchers(HttpMethod.POST, RestApiRoutes.REGISTER_GUEST).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, RestApiRoutes.GET_REPORT).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, RestApiRoutes.CREATE_ROOM).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, RestApiRoutes.UPDATE_ROOM).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PATCH, RestApiRoutes.UPDATE_PARTIALLY_ROOM).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, RestApiRoutes.DELETE_ROOM).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PATCH, RestApiRoutes.UPDATE_PARTIALLY_BOOKING).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.GET, RestApiRoutes.GET_BOOKING_HISTORY).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PATCH, RestApiRoutes.EDIT_COMMENT_ADMIN).hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, RestApiRoutes.DELETE_COMMENT_ADMIN).hasAuthority("ADMIN");
    }

    private void configureUserAndAdminEndpoints(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry request){
        request
                .requestMatchers(HttpMethod.GET, RestApiRoutes.AUTH_CHECK_JWT).hasAnyAuthority("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, RestApiRoutes.BOOK_ROOM).hasAnyAuthority("USER", "ADMIN")
                .requestMatchers(HttpMethod.DELETE, RestApiRoutes.UNBOOK_ROOM).hasAnyAuthority("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, RestApiRoutes.ADD_COMMENT).hasAnyAuthority("USER", "ADMIN")
                .requestMatchers(HttpMethod.PUT, RestApiRoutes.EDIT_COMMENT).hasAnyAuthority("USER", "ADMIN");
    }

    // Prevent Spring Security from attempting to create or manage local user accounts(the default password from console),
    // as the application relies solely on JWT tokens for authentication.
    @Bean
    public UserDetailsService emptyDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                throw new HotelBffException("No local users, only JWT tokens allowed.", HttpStatus.NOT_FOUND);
            }
        };
    }
}
