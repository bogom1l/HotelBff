package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.hotelbff.api.operations.system.createroom.CreateRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.createroom.CreateRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.system.deleteroom.DeleteRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.deleteroom.DeleteRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.system.getreport.GetReportBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.getreport.GetReportBffOperation;
import com.tinqinacademy.hotelbff.api.operations.system.registerguest.RegisterGuestBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.registerguest.RegisterGuestBffOperation;
import com.tinqinacademy.hotelbff.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.system.updateroom.UpdateRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.updateroom.UpdateRoomBffOperation;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SystemController extends BaseController {
    private final RegisterGuestBffOperation registerGuest;
    private final GetReportBffOperation getReport;
    private final CreateRoomBffOperation createRoom;
    private final UpdateRoomBffOperation updateRoom;
    private final UpdatePartiallyRoomBffOperation updatePartiallyRoom;
    private final DeleteRoomBffOperation deleteRoom;

    @Operation(summary = "Register a guest",
            description = "Register a guest as room renter")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Guest registered successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PostMapping(RestApiRoutes.REGISTER_GUEST)
    public ResponseEntity<?> registerGuest(@RequestBody RegisterGuestBffInput input) {
        return handle(registerGuest.process(input));
    }

    @Operation(summary = "Get report",
            description = "Provides a report based on various criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Report retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
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
        GetReportBffInput input = GetReportBffInput.builder()
                .startDate(startDate)
                .endDate(endDate)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .idCardNumber(idCardNumber)
                .idCardValidity(idCardValidity)
                .idCardIssueAuthority(idCardIssueAuthority)
                .idCardIssueDate(idCardIssueDate)
                .roomNumber(roomNumber)
                .build();

        return handle(getReport.process(input));
    }

    @Operation(summary = "Create a room",
            description = "Create a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Room created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PostMapping(RestApiRoutes.CREATE_ROOM)
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomBffInput input) {
        return handleWithStatus(createRoom.process(input), HttpStatus.CREATED);
    }

    @Operation(summary = "Update a room",
            description = "Update a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PutMapping(RestApiRoutes.UPDATE_ROOM)
    public ResponseEntity<?> updateRoom(@PathVariable String roomId,
                                        @RequestBody UpdateRoomBffInput input) {
        UpdateRoomBffInput updatedInput = input.toBuilder()
                .roomId(roomId)
                .build();

        return handle(updateRoom.process(updatedInput));
    }

    @Operation(summary = "Update partially a room",
            description = "Update partially a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PatchMapping(value = RestApiRoutes.UPDATE_PARTIALLY_ROOM)
    public ResponseEntity<?> updatePartiallyRoom(@PathVariable String roomId,
                                                 @RequestBody UpdatePartiallyRoomBffInput input) {
        UpdatePartiallyRoomBffInput updatedInput = input.toBuilder()
                .roomId(roomId)
                .build();

        return handle(updatePartiallyRoom.process(updatedInput));
    }

    @Operation(summary = "Delete a room",
            description = "Delete a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @DeleteMapping(RestApiRoutes.DELETE_ROOM)
    public ResponseEntity<?> deleteRoom(@PathVariable("roomId") String id) {
        DeleteRoomBffInput input = DeleteRoomBffInput.builder()
                .id(id)
                .build();

        return handle(deleteRoom.process(input));
    }
}
