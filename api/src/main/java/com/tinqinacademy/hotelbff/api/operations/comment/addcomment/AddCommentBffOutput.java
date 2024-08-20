package com.tinqinacademy.hotelbff.api.operations.comment.addcomment;

import com.tinqinacademy.hotelbff.api.base.OperationOutput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AddCommentBffOutput implements OperationOutput {
    private UUID commentId;
}
