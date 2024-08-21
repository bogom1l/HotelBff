package com.tinqinacademy.hotelbff.core.converters.comment.editcommentadmin;

import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminOutput;
import com.tinqinacademy.hotelbff.api.operations.comment.editcommentadmin.EditCommentAdminBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EditCommentAdminOutputToEditCommentAdminBffOutput implements Converter<EditCommentAdminOutput, EditCommentAdminBffOutput> {
    @Override
    public EditCommentAdminBffOutput convert(EditCommentAdminOutput source) {
        log.info("Started Converter - EditCommentAdminOutput to EditCommentAdminBffOutput");

        EditCommentAdminBffOutput target = EditCommentAdminBffOutput.builder()
                .commentId(source.getCommentId())
                .build();

        log.info("Ended Converter - EditCommentAdminOutput to EditCommentAdminBffOutput with target: {}", target);
        return target;
    }
}
