package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminInput;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.hotel.api.operations.system.createroom.CreateRoomInput;
import com.tinqinacademy.hotel.api.operations.system.registerguest.RegisterGuestInput;
import com.tinqinacademy.hotel.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomInput;
import com.tinqinacademy.hotel.api.operations.system.updateroom.UpdateRoomInput;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.domain.CommentsRestClient;
import com.tinqinacademy.hotelbff.domain.HotelRestClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SystemController extends BaseController {

    private final HotelRestClient hotelRestClient;
    private final CommentsRestClient commentsRestClient;

    public SystemController(HotelRestClient hotelRestClient, CommentsRestClient commentsRestClient) {
        this.hotelRestClient = hotelRestClient;
        this.commentsRestClient = commentsRestClient;
    }


    @PostMapping(RestApiRoutes.REGISTER_GUEST)
    public ResponseEntity<?> registerGuest(@RequestBody RegisterGuestInput input) {
        return hotelRestClient.registerGuest(input);
    }

    @GetMapping(RestApiRoutes.GET_REPORT)
    public ResponseEntity<?> getReport(@RequestParam(required = false) String startDate,
                                       @RequestParam(required = false) String endDate,
                                       @RequestParam(required = false) String firstName,
                                       @RequestParam(required = false) String lastName,
                                       @RequestParam(required = false) String phoneNumber,
                                       @RequestParam(required = false) String idCardNumber,
                                       @RequestParam(required = false) String idCardValidity,
                                       @RequestParam(required = false) String idCardIssueAuthority,
                                       @RequestParam(required = false) String idCardIssueDate,
                                       @RequestParam(required = false) String roomNumber) {
        return hotelRestClient.getReport(startDate, endDate, firstName, lastName, phoneNumber, idCardNumber,
                idCardValidity, idCardIssueAuthority, idCardIssueDate, roomNumber);
    }

    @PostMapping(RestApiRoutes.CREATE_ROOM)
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomInput input) {
        return hotelRestClient.createRoom(input);
    }

    @PutMapping(RestApiRoutes.UPDATE_ROOM)
    public ResponseEntity<?> updateRoom(@PathVariable String roomId,
                                        @RequestBody UpdateRoomInput input) {
        return hotelRestClient.updateRoom(roomId, input);
    }

    //, consumes = "application/json-patch+json"
    @PatchMapping(value = RestApiRoutes.UPDATE_PARTIALLY_ROOM)
    public ResponseEntity<?> updatePartiallyRoom(@PathVariable String roomId,
                                                 @RequestBody UpdatePartiallyRoomInput input) {
        return hotelRestClient.updatePartiallyRoom(roomId, input);
    }

    @DeleteMapping(RestApiRoutes.DELETE_ROOM)
    public ResponseEntity<?> deleteRoom(@PathVariable("roomId") String id) {
        return hotelRestClient.deleteRoom(id);
    }

    @GetMapping(RestApiRoutes.GET_ALL_USERS_BY_PARTIAL_NAME)
    public ResponseEntity<?> getAllUsersByPartialName(@RequestParam(required = false) String partialName) {
        return hotelRestClient.getAllUsersByPartialName(partialName);
    }

    // ----- Comments -----

    @PatchMapping(RestApiRoutes.EDIT_COMMENT_ADMIN)
    public ResponseEntity<?> editCommentAdmin(@PathVariable String commentId,
                                              @RequestBody EditCommentAdminInput input) {
        return commentsRestClient.editCommentAdmin(commentId, input);
    }

    @DeleteMapping(RestApiRoutes.DELETE_COMMENT_ADMIN)
    ResponseEntity<?> deleteCommentAdmin(@PathVariable String commentId) {
        return commentsRestClient.deleteCommentAdmin(commentId);
    }

}
