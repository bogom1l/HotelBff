package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.domain.feignclients.AuthRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController extends BaseController {
    private final AuthRestClient authRestClient;

    @GetMapping(RestApiRoutes.AUTH_CHECK_JWT)
    public ResponseEntity<?> testValidateJwt(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorizationHeader) {
        return ResponseEntity.ok(authRestClient.validateJwt(authorizationHeader));
    }
}
