package com.tinqinacademy.hotelbff.api.operations.comment.deletecommentadmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.hotelbff.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class DeleteCommentAdminBffInput implements OperationInput {
    @JsonIgnore
    private String commentId;
}
