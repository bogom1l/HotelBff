package com.tinqinacademy.hotelbff.core.converters.comment.editcomment;

import com.tinqinacademy.comments.api.operations.editcomment.EditCommentOutput;
import com.tinqinacademy.hotelbff.api.operations.comment.editcomment.EditCommentBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EditCommentOutputToEditCommentBffOutput implements Converter<EditCommentOutput, EditCommentBffOutput> {
    @Override
    public EditCommentBffOutput convert(EditCommentOutput source) {
        log.info("Started Converter - EditCommentOutput to EditCommentBffOutput");

        EditCommentBffOutput target = EditCommentBffOutput.builder()
                .commentId(String.valueOf(source.getCommentId()))
                .build();

        log.info("Ended Converter - EditCommentOutput to EditCommentBffOutput");
        return target;
    }
}
