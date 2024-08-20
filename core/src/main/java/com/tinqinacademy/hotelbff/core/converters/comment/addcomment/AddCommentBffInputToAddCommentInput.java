package com.tinqinacademy.hotelbff.core.converters.comment.addcomment;


import com.tinqinacademy.comments.api.operations.addcomment.AddCommentInput;
import com.tinqinacademy.hotelbff.api.operations.comments.addcomment.AddCommentBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AddCommentBffInputToAddCommentInput implements Converter<AddCommentBffInput, AddCommentInput> {
    @Override
    public AddCommentInput convert(AddCommentBffInput source) {
        log.info("Started Converter - AddCommentBffInput to AddCommentInput");

        AddCommentInput target = AddCommentInput.builder()
                .roomId(source.getRoomId())
                .userId(source.getUserId())
                .content(source.getContent())
                .build();

        log.info("Ended Converter - AddCommentBffInput to AddCommentInput");
        return target;
    }
}
