package com.tinqinacademy.hotelbff.rest.controllers;

import com.tinqinacademy.hotelbff.api.operations.comment.addcomment.AddCommentBffInput;
import com.tinqinacademy.hotelbff.api.operations.comment.addcomment.AddCommentBffOperation;
import com.tinqinacademy.hotelbff.api.operations.comment.deletecommentadmin.DeleteCommentAdminBffInput;
import com.tinqinacademy.hotelbff.api.operations.comment.deletecommentadmin.DeleteCommentAdminBffOperation;
import com.tinqinacademy.hotelbff.api.operations.comment.editcomment.EditCommentBffInput;
import com.tinqinacademy.hotelbff.api.operations.comment.editcomment.EditCommentBffOperation;
import com.tinqinacademy.hotelbff.api.operations.comment.editcommentadmin.EditCommentAdminBffInput;
import com.tinqinacademy.hotelbff.api.operations.comment.editcommentadmin.EditCommentAdminBffOperation;
import com.tinqinacademy.hotelbff.api.operations.comment.getcomments.GetCommentsBffInput;
import com.tinqinacademy.hotelbff.api.operations.comment.getcomments.GetCommentsBffOperation;
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
    private final EditCommentBffOperation editComment;
    private final EditCommentAdminBffOperation editCommentAdmin;
    private final DeleteCommentAdminBffOperation deleteCommentAdmin;
    private final UserContext userContext;

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

    @Operation(summary = "Add a comment for a room",
            description = "Add a comment for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully added comment"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @PostMapping(RestApiRoutes.ADD_COMMENT)
    public ResponseEntity<?> addComment(@PathVariable String roomId,
                                        @RequestBody AddCommentBffInput input) {
        AddCommentBffInput updatedInput = input.toBuilder()
                .roomId(roomId)
                .userId(userContext.getUserId())
                .build();

        return handleWithStatus(addComment.process(updatedInput), HttpStatus.CREATED);
    }


    @Operation(summary = "Edit own comment for a room",
            description = "Edit own comment for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited comment"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @PutMapping(RestApiRoutes.EDIT_COMMENT)
    public ResponseEntity<?> editComment(@PathVariable String commentId,
                                         @RequestBody EditCommentBffInput input) {
        EditCommentBffInput updatedInput = input.toBuilder()
                .commentId(commentId)
                .userId(userContext.getUserId())
                .build();

        return handle(editComment.process(updatedInput));
    }

    @Operation(summary = "Edit any comment for a room",
            description = "Edit any comment for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully edited comment"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @PatchMapping(RestApiRoutes.EDIT_COMMENT_ADMIN)
    public ResponseEntity<?> editCommentAdmin(@PathVariable String commentId,
                                              @RequestBody EditCommentAdminBffInput input) {
        EditCommentAdminBffInput updatedInput = input.toBuilder()
                .commentId(commentId)
                .userId(userContext.getUserId())
                .build();

        return handle(editCommentAdmin.process(updatedInput));
    }

    @Operation(summary = "Delete any comment for a room",
            description = "Delete any comment for a room")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted comment"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Room not found")})
    @DeleteMapping(RestApiRoutes.DELETE_COMMENT_ADMIN)
    ResponseEntity<?> deleteCommentAdmin(@PathVariable String commentId) {
        DeleteCommentAdminBffInput input = DeleteCommentAdminBffInput
                .builder()
                .commentId(commentId)
                .build();

        return handle(deleteCommentAdmin.process(input));
    }

}
