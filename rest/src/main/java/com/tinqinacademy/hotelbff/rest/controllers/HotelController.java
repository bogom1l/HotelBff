package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.bookroom.BookRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.bookroom.BookRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.checkavailableroom.CheckAvailableRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.checkavailableroom.CheckAvailableRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.checkavailableroom.CheckAvailableRoomBffOutput;
import com.tinqinacademy.hotelbff.api.operations.unbookroom.UnbookRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.unbookroom.UnbookRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.getroombasicinfo.GetRoomBasicInfoBffInput;
import com.tinqinacademy.hotelbff.api.operations.getroombasicinfo.GetRoomBasicInfoBffOperation;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.domain.CommentsRestClient;
import com.tinqinacademy.hotelbff.domain.HotelRestClient;
import com.tinqinacademy.hotelbff.rest.configuration.UserContext;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class HotelController extends BaseController {

    private final HotelRestClient hotelRestClient;
    private final CommentsRestClient commentsRestClient;
    private final CheckAvailableRoomBffOperation checkAvailableRoom;
    private final GetRoomBasicInfoBffOperation getRoomBasicInfo;
    private final BookRoomBffOperation bookRoom;
    private final UserContext userContext;
    private final UnbookRoomBffOperation unbookRoom;


    @GetMapping(RestApiRoutes.CHECK_ROOM_AVAILABILITY)
    public ResponseEntity<?> checkAvailableRoom(@RequestParam(required = false) LocalDate startDate,
                                                @RequestParam(required = false) LocalDate endDate,
                                                @RequestParam(required = false) String bedSize,
                                                @RequestParam(required = false) String bathroomType) {
        CheckAvailableRoomBffInput input = CheckAvailableRoomBffInput.builder()
                .startDate(startDate)
                .endDate(endDate)
                .bedSize(bedSize)
                .bathroomType(bathroomType)
                .build();

        Either<ErrorsWrapper, CheckAvailableRoomBffOutput> output = checkAvailableRoom.process(input);
        return handle(output);
    }

    @GetMapping(RestApiRoutes.GET_ROOM_INFO)
    public ResponseEntity<?> getRoomBasicInfo(@PathVariable String roomId) {
        GetRoomBasicInfoBffInput input = GetRoomBasicInfoBffInput.builder()
                .roomId(roomId)
                .build();

        return handle(getRoomBasicInfo.process(input));
    }

    @PostMapping(RestApiRoutes.BOOK_ROOM)
    public ResponseEntity<?> bookRoom(@PathVariable String roomId,
                                      @RequestBody BookRoomBffInput input) {
        BookRoomBffInput updatedInput = input.toBuilder()
                .roomId(roomId)
                .userContextId(userContext.getUserId())
                .build();

        return handleWithStatus(bookRoom.process(updatedInput), HttpStatus.CREATED);
    }

    @DeleteMapping(RestApiRoutes.UNBOOK_ROOM)
    public ResponseEntity<?> unbookRoom(@PathVariable String bookingId,
                                        @RequestBody UnbookRoomBffInput input) {
        UnbookRoomBffInput updatedInput = input.toBuilder()
                .bookingId(bookingId)
                .userContextId(userContext.getUserId())
                .build();

        return handle(unbookRoom.process(updatedInput));
    }
//
//    //, consumes = "application/json-patch+json"
//    @PatchMapping(value = RestApiRoutes.UPDATE_PARTIALLY_BOOKING)
//    public ResponseEntity<?> updatePartiallyBooking(@PathVariable String bookingId,
//                                                    @RequestBody UpdatePartiallyBookingInput input) {
//        return hotelRestClient.updatePartiallyBooking(bookingId, input);
//    }
//
//    @GetMapping(RestApiRoutes.GET_BOOKING_HISTORY)
//    public ResponseEntity<?> getBookingHistory(@PathVariable String phoneNumber) {
//        return hotelRestClient.getBookingHistory(phoneNumber);
//    }

    //------- Comments -------

//    @GetMapping(RestApiRoutes.GET_ALL_COMMENTS)
//    public ResponseEntity<?> getAllComments(@PathVariable String roomId) {
//        return commentsRestClient.getAllComments(roomId);
//    }
//
//    @PostMapping(RestApiRoutes.ADD_COMMENT)
//    public ResponseEntity<?> addComment(@PathVariable String roomId,
//                                        @RequestBody AddCommentInput input) {
//        return commentsRestClient.addComment(roomId, input);
//    }
//
//    @PutMapping(RestApiRoutes.EDIT_COMMENT)
//    public ResponseEntity<?> editComment(@PathVariable String commentId,
//                                         @RequestBody EditCommentInput input) {
//        return commentsRestClient.editComment(commentId, input);
//    }

}