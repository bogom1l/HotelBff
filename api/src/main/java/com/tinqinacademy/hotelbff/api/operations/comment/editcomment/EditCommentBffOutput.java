package com.tinqinacademy.hotelbff.api.operations.comment.editcomment;


import com.tinqinacademy.hotelbff.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EditCommentBffOutput implements OperationOutput {
    private String commentId;
}
