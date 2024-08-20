package com.tinqinacademy.hotelbff.api.operations.comment.addcomment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.hotelbff.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class AddCommentBffInput implements OperationInput {
    @JsonIgnore
    private String roomId;

    @JsonIgnore
    private String userId;

    private String content;
}
