package com.tinqinacademy.hotelbff.core.converters.comment.deletecommentadmin;

import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminOutput;
import com.tinqinacademy.hotelbff.api.operations.comment.deletecommentadmin.DeleteCommentAdminBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeleteCommentAdminOutputToDeleteCommentAdminBffOutput implements Converter<DeleteCommentAdminOutput, DeleteCommentAdminBffOutput> {
    @Override
    public DeleteCommentAdminBffOutput convert(DeleteCommentAdminOutput source) {
        log.info("Started Converter - DeleteCommentAdminOutput to DeleteCommentAdminBffOutput");

        DeleteCommentAdminBffOutput target = DeleteCommentAdminBffOutput.builder()
                .build();

        log.info("Ended Converter - DeleteCommentAdminOutput to DeleteCommentAdminBffOutput");
        return target;
    }
}
