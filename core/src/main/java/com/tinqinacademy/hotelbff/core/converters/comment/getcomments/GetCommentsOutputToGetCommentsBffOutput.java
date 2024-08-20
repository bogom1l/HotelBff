package com.tinqinacademy.hotelbff.core.converters.comment.getcomments;

import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsOutput;
import com.tinqinacademy.hotelbff.api.operations.comment.getcomments.CommentBffOutput;
import com.tinqinacademy.hotelbff.api.operations.comment.getcomments.GetCommentsBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetCommentsOutputToGetCommentsBffOutput implements Converter<GetCommentsOutput, GetCommentsBffOutput> {

    private final ConversionService conversionService;

    @Lazy
    public GetCommentsOutputToGetCommentsBffOutput(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public GetCommentsBffOutput convert(GetCommentsOutput source) {
        log.info("Started Converter - GetCommentsOutput to GetCommentsBffOutput");

        GetCommentsBffOutput target = GetCommentsBffOutput.builder()
                .comments(source.getComments().stream()
                        .map(comment -> conversionService.convert(comment, CommentBffOutput.class))
                        .toList())
                .build();

        log.info("Ended Converter - GetCommentsOutput to GetCommentsBffOutput");
        return target;
    }
}
