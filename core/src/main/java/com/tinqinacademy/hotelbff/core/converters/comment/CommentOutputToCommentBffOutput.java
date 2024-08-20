package com.tinqinacademy.hotelbff.core.converters.comment;

import com.tinqinacademy.comments.api.operations.getcomments.CommentOutput;
import com.tinqinacademy.hotelbff.api.operations.comments.CommentBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommentOutputToCommentBffOutput implements Converter<CommentOutput, CommentBffOutput> {
    @Override
    public CommentBffOutput convert(CommentOutput source) {
        log.info("Started Converter - CommentOutput to CommentBffOutput");

        CommentBffOutput target = CommentBffOutput.builder()
                .id(source.getId())
                .content(source.getContent())
                .publishDate(source.getPublishDate())
                .lastEditedDate(source.getLastEditedDate())
                .userId(source.getUserId())
                .lastEditedBy(source.getLastEditedBy())
                .build();

        log.info("Ended Converter - CommentOutput to CommentBffOutput");
        return target;
    }
}
