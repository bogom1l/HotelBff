package com.tinqinacademy.hotelbff.api.operations.comment.editcomment;

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
public class EditCommentBffInput implements OperationInput {
    @JsonIgnore
    private String commentId;

    @JsonIgnore
    private String userId;

    @NotBlank
    private String content;
}
