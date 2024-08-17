package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.hotel.api.operations.hotel.getbookinghistory.GetBookingHistoryInput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.hotel.bookroom.BookRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.bookroom.BookRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.checkavailableroom.CheckAvailableRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.checkavailableroom.CheckAvailableRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.checkavailableroom.CheckAvailableRoomBffOutput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.GetBookingHistoryBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.GetBookingHistoryBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.unbookroom.UnbookRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.unbookroom.UnbookRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.getroombasicinfo.GetRoomBasicInfoBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getroombasicinfo.GetRoomBasicInfoBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingBffOperation;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.domain.feignclients.CommentsRestClient;
import com.tinqinacademy.hotelbff.domain.feignclients.HotelRestClient;
import com.tinqinacademy.hotelbff.rest.configuration.UserContext;
import io.swagger.v3.oas.annotations.Operation;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UpdatePartiallyBookingBffOperation updatePartiallyBooking;
    private final GetBookingHistoryBffOperation getBookingHistory;


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

    @Operation(summary = "Update partially a booking")
    @PatchMapping(RestApiRoutes.UPDATE_PARTIALLY_BOOKING)    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updatePartiallyBooking(@PathVariable String bookingId,
                                                    @RequestBody UpdatePartiallyBookingBffInput input) {
        UpdatePartiallyBookingBffInput updatedInput = input.toBuilder()
                .bookingId(bookingId)
                .build();

        return handle(updatePartiallyBooking.process(updatedInput));
    }

    @Operation(summary = "Get booking history")
    @GetMapping(RestApiRoutes.GET_BOOKING_HISTORY)
    public ResponseEntity<?> getBookingHistory(@PathVariable String userId) {

        GetBookingHistoryBffInput input = GetBookingHistoryBffInput.builder()
                .userContextId(userId)
                .build();

        var asd = getBookingHistory.process(input);

        return handle(asd);
    }

    //------- Comments -------
    //todo - in new Controller

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