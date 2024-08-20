package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.hotelbff.api.operations.comments.addcomment.AddCommentBffInput;
import com.tinqinacademy.hotelbff.api.operations.comments.addcomment.AddCommentBffOperation;
import com.tinqinacademy.hotelbff.api.operations.comments.getcomments.GetCommentsBffInput;
import com.tinqinacademy.hotelbff.api.operations.comments.getcomments.GetCommentsBffOperation;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.rest.configuration.UserContext;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController extends BaseController {

    private final GetCommentsBffOperation getComments;
    private final AddCommentBffOperation addComment;
    private final UserContext userContext;

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

//todo add @Operation and @ApiResponses

    @Operation(summary = "Get all comments for a room",
            description = "Get all comments for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved comments"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @GetMapping(RestApiRoutes.GET_ALL_COMMENTS)
    public ResponseEntity<?> getAllComments(@PathVariable String roomId) {
        GetCommentsBffInput input = GetCommentsBffInput.builder()
                .roomId(roomId)
                .build();
        return handle(getComments.process(input));
    }

    @PostMapping(RestApiRoutes.ADD_COMMENT)
    public ResponseEntity<?> addComment(@PathVariable String roomId,
                                        @RequestBody AddCommentBffInput input) {
        AddCommentBffInput updatedInput = input.toBuilder()
                .roomId(roomId)
                .userId(userContext.getUserId())
                .build();

        return handleWithStatus(addComment.process(updatedInput), HttpStatus.CREATED);
    }

//
//    @PutMapping(RestApiRoutes.EDIT_COMMENT)
//    public ResponseEntity<?> editComment(@PathVariable String commentId,
//                                         @RequestBody EditCommentInput input) {
//        return commentsRestClient.editComment(commentId, input);
//    }
//
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
