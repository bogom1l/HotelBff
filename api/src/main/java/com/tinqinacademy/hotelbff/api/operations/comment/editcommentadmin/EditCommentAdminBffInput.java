package com.tinqinacademy.hotelbff.api.operations.comment.editcommentadmin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.hotelbff.api.base.OperationInput;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class EditCommentAdminBffInput implements OperationInput {
    @JsonIgnore
    private String commentId;

    @JsonIgnore
    private String userId;

    @NotBlank
    private String content;

    //todo? private String roomNumber;
}
