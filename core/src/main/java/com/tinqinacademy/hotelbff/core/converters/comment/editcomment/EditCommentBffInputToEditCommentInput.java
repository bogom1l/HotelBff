package com.tinqinacademy.hotelbff.core.converters.comment.editcomment;

import com.tinqinacademy.comments.api.operations.editcomment.EditCommentInput;
import com.tinqinacademy.hotelbff.api.operations.comment.editcomment.EditCommentBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EditCommentBffInputToEditCommentInput implements Converter<EditCommentBffInput, EditCommentInput> {
    @Override
    public EditCommentInput convert(EditCommentBffInput source) {
        log.info("Started Converter - EditCommentInput to EditCommentBffInput");

        EditCommentInput target = EditCommentInput.builder()
                .commentId(source.getCommentId())
                .userId(source.getUserId())
                .content(source.getContent())
                .build();

        log.info("Ended Converter - EditCommentInput to EditCommentBffInput");
        return target;
    }
}
