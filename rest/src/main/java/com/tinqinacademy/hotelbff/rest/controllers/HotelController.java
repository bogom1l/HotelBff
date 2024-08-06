package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.hotel.api.operations.hotel.bookroom.BookRoomInput;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.domain.HotelRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class HotelController extends BaseController {

    private final HotelRestClient hotelRestClient;

    public HotelController(HotelRestClient hotelRestClient) {
        this.hotelRestClient = hotelRestClient;
    }

    @GetMapping(RestApiRoutes.GET_ROOM_INFO)
    public ResponseEntity<?> getRoomBasicInfo(@PathVariable String roomId) {
        return hotelRestClient.getRoomBasicInfo(roomId);
    }

    @GetMapping(RestApiRoutes.CHECK_ROOM_AVAILABILITY)
    public ResponseEntity<?> checkAvailableRoom(@RequestParam(required = false) LocalDate startDate,
                                                @RequestParam(required = false) LocalDate endDate,
                                                @RequestParam(required = false) String bedSize,
                                                @RequestParam(required = false) String bathroomType) {
        return hotelRestClient.checkAvailableRoom(startDate, endDate, bedSize, bathroomType);
    }

    @PostMapping(com.tinqinacademy.hotel.api.restroutes.RestApiRoutes.BOOK_ROOM)
    public ResponseEntity<?> bookRoom(@PathVariable String roomId,
                                      @RequestBody BookRoomInput input) {
        return hotelRestClient.bookRoom(roomId, input);
    }


}