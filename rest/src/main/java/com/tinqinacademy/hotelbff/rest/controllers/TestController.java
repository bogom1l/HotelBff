package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.domain.AuthRestClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends BaseController{

    private final AuthRestClient authRestClient;

    public TestController(AuthRestClient authRestClient) {
        this.authRestClient = authRestClient;
    }

    @GetMapping(RestApiRoutes.AUTH_CHECK_JWT)
    public ResponseEntity<?> testValidateJwt(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authHeader) {

        String test = "test";
        return ResponseEntity.ok(authRestClient.validateJwt(authHeader));
    }

}
