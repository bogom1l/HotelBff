package com.tinqinacademy.hotelbff.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController extends BaseController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

//todo add @Operation and @ApiResponses

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
