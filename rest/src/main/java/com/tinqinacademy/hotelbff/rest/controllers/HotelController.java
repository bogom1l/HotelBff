package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.comments.api.operations.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentInput;
import com.tinqinacademy.hotel.api.operations.hotel.bookroom.BookRoomInput;
import com.tinqinacademy.hotel.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingInput;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.domain.AuthRestClient;
import com.tinqinacademy.hotelbff.domain.CommentsRestClient;
import com.tinqinacademy.hotelbff.domain.HotelRestClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class HotelController extends BaseController {

    private final HotelRestClient hotelRestClient;
    private final CommentsRestClient commentsRestClient;
    private final AuthRestClient authRestClient;

    public HotelController(HotelRestClient hotelRestClient, CommentsRestClient commentsRestClient, AuthRestClient authRestClient) {
        this.hotelRestClient = hotelRestClient;
        this.commentsRestClient = commentsRestClient;
        this.authRestClient = authRestClient;
    }

    @GetMapping(RestApiRoutes.CHECK_ROOM_AVAILABILITY)
    public ResponseEntity<?> checkAvailableRoom(@RequestParam(required = false) LocalDate startDate,
                                                @RequestParam(required = false) LocalDate endDate,
                                                @RequestParam(required = false) String bedSize,
                                                @RequestParam(required = false) String bathroomType) {
        return hotelRestClient.checkAvailableRoom(startDate, endDate, bedSize, bathroomType);
    }

    @GetMapping(RestApiRoutes.GET_ROOM_INFO)
    public ResponseEntity<?> getRoomBasicInfo(@PathVariable String roomId) {
        return hotelRestClient.getRoomBasicInfo(roomId);
    }

    @PostMapping(RestApiRoutes.BOOK_ROOM)
    public ResponseEntity<?> bookRoom(@PathVariable String roomId,
                                      @RequestBody BookRoomInput input) {
        return hotelRestClient.bookRoom(roomId, input);
    }

    @DeleteMapping(RestApiRoutes.UNBOOK_ROOM)
    public ResponseEntity<?> unbookRoom(@PathVariable String bookingId) {
        return hotelRestClient.unbookRoom(bookingId);
    }

    //, consumes = "application/json-patch+json"
    @PatchMapping(value = RestApiRoutes.UPDATE_PARTIALLY_BOOKING)
    public ResponseEntity<?> updatePartiallyBooking(@PathVariable String bookingId,
                                                    @RequestBody UpdatePartiallyBookingInput input) {
        return hotelRestClient.updatePartiallyBooking(bookingId, input);
    }

    @GetMapping(RestApiRoutes.GET_BOOKING_HISTORY)
    public ResponseEntity<?> getBookingHistory(@PathVariable String phoneNumber) {
        return hotelRestClient.getBookingHistory(phoneNumber);
    }

    //------- Comments -------

    @GetMapping(RestApiRoutes.GET_ALL_COMMENTS)
    public ResponseEntity<?> getAllComments(@PathVariable String roomId) {
        return commentsRestClient.getAllComments(roomId);
    }

    @PostMapping(RestApiRoutes.ADD_COMMENT)
    public ResponseEntity<?> addComment(@PathVariable String roomId,
                                        @RequestBody AddCommentInput input) {
        return commentsRestClient.addComment(roomId, input);
    }

    @PutMapping(RestApiRoutes.EDIT_COMMENT)
    public ResponseEntity<?> editComment(@PathVariable String commentId,
                                         @RequestBody EditCommentInput input) {
        return commentsRestClient.editComment(commentId, input);
    }

}