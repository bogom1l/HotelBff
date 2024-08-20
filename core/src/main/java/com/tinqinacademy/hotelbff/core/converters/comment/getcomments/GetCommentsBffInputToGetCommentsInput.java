package com.tinqinacademy.hotelbff.core.converters.comment.getcomments;

import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsInput;
import com.tinqinacademy.hotelbff.api.operations.comment.getcomments.GetCommentsBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetCommentsBffInputToGetCommentsInput implements Converter<GetCommentsBffInput, GetCommentsInput> {
    @Override
    public GetCommentsInput convert(GetCommentsBffInput source) {
        log.info("Started Converter - GetCommentsBffInput to GetCommentsInput");

        GetCommentsInput target = GetCommentsInput.builder()
                .roomId(source.getRoomId())
                .build();

        log.info("Ended Converter - GetCommentsBffInput to GetCommentsInput");
        return target;
    }
}
