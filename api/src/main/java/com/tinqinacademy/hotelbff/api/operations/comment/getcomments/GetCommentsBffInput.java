package com.tinqinacademy.hotelbff.api.operations.comment.getcomments;

import com.tinqinacademy.hotelbff.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetCommentsBffInput implements OperationInput {
    private String roomId;
}
