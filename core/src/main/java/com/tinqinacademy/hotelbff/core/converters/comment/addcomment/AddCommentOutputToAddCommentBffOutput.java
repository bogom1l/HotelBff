package com.tinqinacademy.hotelbff.core.converters.comment.addcomment;

import com.tinqinacademy.comments.api.operations.addcomment.AddCommentOutput;
import com.tinqinacademy.hotelbff.api.operations.comment.addcomment.AddCommentBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AddCommentOutputToAddCommentBffOutput implements Converter<AddCommentOutput, AddCommentBffOutput> {

    @Override
    public AddCommentBffOutput convert(AddCommentOutput source) {
        log.info("Started Converter - AddCommentOutput to AddCommentBffOutput");

        AddCommentBffOutput target = AddCommentBffOutput.builder()
                .commentId(source.getCommentId())
                .build();

        log.info("Ended Converter - AddCommentOutput to AddCommentBffOutput");
        return target;
    }
}
