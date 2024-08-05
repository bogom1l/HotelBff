package com.tinqinacademy.hotelbff.domain;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@FeignClient(name = "hotel", url = "http://localhost:8080")
public interface HotelRestClient {

    @GetMapping("/api/v1/hotel/rooms")
    ResponseEntity<?> checkAvailableRooms(@RequestParam(required = false) LocalDate startDate,
                                       @RequestParam(required = false) LocalDate endDate,
                                       @RequestParam(required = false) String bedSize,
                                       @RequestParam(required = false) String bathroomType);
}
