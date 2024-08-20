package com.tinqinacademy.hotelbff.api.operations.comments;

import com.tinqinacademy.hotelbff.api.base.OperationOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetCommentsBffOutput implements OperationOutput {
    List<CommentBffOutput> comments;
}
