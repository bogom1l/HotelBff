package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.hotel.api.operations.system.updateroom.UpdateRoomInput;
import com.tinqinacademy.hotel.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.domain.HotelRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController extends BaseController{

    private final HotelRestClient hotelRestClient;

    public SystemController(HotelRestClient hotelRestClient) {
        this.hotelRestClient = hotelRestClient;
    }

    @PutMapping(RestApiRoutes.UPDATE_ROOM)
    public ResponseEntity<?> updateRoom(@PathVariable String roomId,
                                        @RequestBody UpdateRoomInput input) {
        return hotelRestClient.updateRoom(roomId, input);
    }


}
