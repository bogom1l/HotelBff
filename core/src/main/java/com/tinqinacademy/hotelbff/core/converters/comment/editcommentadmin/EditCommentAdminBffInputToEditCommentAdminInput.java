package com.tinqinacademy.hotelbff.core.converters.comment.editcommentadmin;

import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.hotelbff.api.operations.comment.editcommentadmin.EditCommentAdminBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EditCommentAdminBffInputToEditCommentAdminInput implements Converter<EditCommentAdminBffInput, EditCommentAdminInput> {
    @Override
    public EditCommentAdminInput convert(EditCommentAdminBffInput source) {
        log.info("Started Converter - EditCommentAdminBffInput to EditCommentAdminInput");

        EditCommentAdminInput target = EditCommentAdminInput.builder()
                .commentId(source.getCommentId())
                .content(source.getContent())
                .userId(source.getUserId())
                .build();

        log.info("Ended Converter - EditCommentAdminBffInput to EditCommentAdminInput with target: {}", target);
        return target;
    }
}
