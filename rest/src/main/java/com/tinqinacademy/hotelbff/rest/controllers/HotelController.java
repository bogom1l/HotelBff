package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.hotel.bookroom.BookRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.bookroom.BookRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.checkavailableroom.CheckAvailableRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.checkavailableroom.CheckAvailableRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.checkavailableroom.CheckAvailableRoomBffOutput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.GetBookingHistoryBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.GetBookingHistoryBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.getroombasicinfo.GetRoomBasicInfoBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getroombasicinfo.GetRoomBasicInfoBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.unbookroom.UnbookRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.unbookroom.UnbookRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingBffOperation;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.rest.configuration.UserContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class HotelController extends BaseController {
    private final CheckAvailableRoomBffOperation checkAvailableRoom;
    private final GetRoomBasicInfoBffOperation getRoomBasicInfo;
    private final BookRoomBffOperation bookRoom;
    private final UserContext userContext;
    private final UnbookRoomBffOperation unbookRoom;
    private final UpdatePartiallyBookingBffOperation updatePartiallyBooking;
    private final GetBookingHistoryBffOperation getBookingHistory;

    @Operation(summary = "Check available room",
            description = "Check room availability for a certain period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room is available"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
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

    @Operation(summary = "Get room basic info",
            description = "Returns basic info for a room with the specified id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room info retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @GetMapping(RestApiRoutes.GET_ROOM_INFO)
    public ResponseEntity<?> getRoomBasicInfo(@PathVariable String roomId) {
        GetRoomBasicInfoBffInput input = GetRoomBasicInfoBffInput.builder()
                .roomId(roomId)
                .build();

        return handle(getRoomBasicInfo.process(input));
    }

    @Operation(summary = "Book a room",
            description = "Book a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Room booked successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PostMapping(RestApiRoutes.BOOK_ROOM)
    public ResponseEntity<?> bookRoom(@PathVariable String roomId,
                                      @RequestBody BookRoomBffInput input) {
        BookRoomBffInput updatedInput = input.toBuilder()
                .roomId(roomId)
                .userContextId(userContext.getUserId())
                .build();

        return handleWithStatus(bookRoom.process(updatedInput), HttpStatus.CREATED);
    }

    @Operation(summary = "Unbook a room",
            description = "Unbook a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room unbooked successfully"),
            @ApiResponse(responseCode = "404", description = "Room booking not found")})
    @DeleteMapping(RestApiRoutes.UNBOOK_ROOM)
    public ResponseEntity<?> unbookRoom(@PathVariable String bookingId,
                                        @RequestBody UnbookRoomBffInput input) {
        UnbookRoomBffInput updatedInput = input.toBuilder()
                .bookingId(bookingId)
                .userContextId(userContext.getUserId())
                .build();

        return handle(unbookRoom.process(updatedInput));
    }

    @Operation(summary = "Update partially a booking",
            description = "Update partially a booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PatchMapping(RestApiRoutes.UPDATE_PARTIALLY_BOOKING)    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updatePartiallyBooking(@PathVariable String bookingId,
                                                    @RequestBody UpdatePartiallyBookingBffInput input) {
        UpdatePartiallyBookingBffInput updatedInput = input.toBuilder()
                .bookingId(bookingId)
                .build();

        return handle(updatePartiallyBooking.process(updatedInput));
    }

    @Operation(summary = "Get booking history",
            description = "Get booking history")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking history retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @GetMapping(RestApiRoutes.GET_BOOKING_HISTORY)
    public ResponseEntity<?> getBookingHistory(@PathVariable String userId) {
        GetBookingHistoryBffInput input = GetBookingHistoryBffInput.builder()
                .userContextId(userId)
                .build();

        return handle(getBookingHistory.process(input));
    }

}