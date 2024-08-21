package com.tinqinacademy.hotelbff.core.converters.comment.deletecommentadmin;

import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminInput;
import com.tinqinacademy.hotelbff.api.operations.comment.deletecommentadmin.DeleteCommentAdminBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeleteCommentAdminBffInputToDeleteCommentAdminInput implements Converter<DeleteCommentAdminBffInput, DeleteCommentAdminInput> {
    @Override
    public DeleteCommentAdminInput convert(DeleteCommentAdminBffInput source) {
        log.info("Started Converter - DeleteCommentAdminBffInput to DeleteCommentAdminInput");

        DeleteCommentAdminInput target = DeleteCommentAdminInput.builder()
                .commentId(source.getCommentId())
                .build();

        log.info("Ended Converter - DeleteCommentAdminBffInput to DeleteCommentAdminInput");
        return target;
    }
}
