package com.tinqinacademy.hotelbff.api.operations.comment.editcommentadmin;

import com.tinqinacademy.hotelbff.api.base.OperationOutput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EditCommentAdminBffOutput implements OperationOutput {
    private UUID commentId;
}
