package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.hotelbff.api.operations.system.createroom.CreateRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.createroom.CreateRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.system.getreport.GetReportBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.getreport.GetReportBffOperation;
import com.tinqinacademy.hotelbff.api.operations.system.registerguest.RegisterGuestBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.registerguest.RegisterGuestBffOperation;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.domain.feignclients.CommentsRestClient;
import com.tinqinacademy.hotelbff.domain.feignclients.HotelRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SystemController extends BaseController {

    private final HotelRestClient hotelRestClient;
    private final CommentsRestClient commentsRestClient;
    private final RegisterGuestBffOperation registerGuest;
    private final GetReportBffOperation getReport;
    private final CreateRoomBffOperation createRoom;


    @PostMapping(RestApiRoutes.REGISTER_GUEST)
    public ResponseEntity<?> registerGuest(@RequestBody RegisterGuestBffInput input) {
        return handle(registerGuest.process(input));
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

    @PostMapping(RestApiRoutes.CREATE_ROOM)
    public ResponseEntity<?> createRoom(@RequestBody CreateRoomBffInput input) {
        return handle(createRoom.process(input));
    }
//
//    @PutMapping(RestApiRoutes.UPDATE_ROOM)
//    public ResponseEntity<?> updateRoom(@PathVariable String roomId,
//                                        @RequestBody UpdateRoomInput input) {
//        return hotelRestClient.updateRoom(roomId, input);
//    }
//
//    //, consumes = "application/json-patch+json"
//    @PatchMapping(value = RestApiRoutes.UPDATE_PARTIALLY_ROOM)
//    public ResponseEntity<?> updatePartiallyRoom(@PathVariable String roomId,
//                                                 @RequestBody UpdatePartiallyRoomInput input) {
//        return hotelRestClient.updatePartiallyRoom(roomId, input);
//    }
//
//    @DeleteMapping(RestApiRoutes.DELETE_ROOM)
//    public ResponseEntity<?> deleteRoom(@PathVariable("roomId") String id) {
//        return hotelRestClient.deleteRoom(id);
//    }
//
//    @GetMapping(RestApiRoutes.GET_ALL_USERS_BY_PARTIAL_NAME)
//    public ResponseEntity<?> getAllUsersByPartialName(@RequestParam(required = false) String partialName) {
//        return hotelRestClient.getAllUsersByPartialName(partialName);
//    }

    // ----- Comments -----

//    @PatchMapping(RestApiRoutes.EDIT_COMMENT_ADMIN)
//    public ResponseEntity<?> editCommentAdmin(@PathVariable String commentId,
//                                              @RequestBody EditCommentAdminInput input) {
//        return commentsRestClient.editCommentAdmin(commentId, input);
//    }
//
//    @DeleteMapping(RestApiRoutes.DELETE_COMMENT_ADMIN)
//    ResponseEntity<?> deleteCommentAdmin(@PathVariable String commentId) {
//        return commentsRestClient.deleteCommentAdmin(commentId);
//    }

}
