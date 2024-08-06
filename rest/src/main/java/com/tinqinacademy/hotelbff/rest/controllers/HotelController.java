package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.domain.HotelRestClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController extends BaseController {

    private final HotelRestClient hotelRestClient;

    public HotelController(HotelRestClient hotelRestClient) {
        this.hotelRestClient = hotelRestClient;
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<?> getRoomBasicInfo(@PathVariable String roomId) {
        return hotelRestClient.getRoomBasicInfo(roomId);
    }



}