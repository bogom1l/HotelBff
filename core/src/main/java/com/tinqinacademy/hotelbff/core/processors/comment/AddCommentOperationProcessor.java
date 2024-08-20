package com.tinqinacademy.hotelbff.core.processors.comment;

import com.tinqinacademy.comments.api.operations.addcomment.AddCommentInput;
import com.tinqinacademy.comments.api.operations.addcomment.AddCommentOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.comments.addcomment.AddCommentBffInput;
import com.tinqinacademy.hotelbff.api.operations.comments.addcomment.AddCommentBffOperation;
import com.tinqinacademy.hotelbff.api.operations.comments.addcomment.AddCommentBffOutput;
import com.tinqinacademy.hotelbff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.hotelbff.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.hotelbff.domain.feignclients.CommentsRestClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddCommentOperationProcessor extends BaseOperationProcessor<AddCommentBffInput> implements AddCommentBffOperation {
    private final CommentsRestClient commentsRestClient;

    protected AddCommentOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, CommentsRestClient commentsRestClient) {
        super(conversionService, errorHandler, validator);
        this.commentsRestClient = commentsRestClient;
    }

    @Override
    public Either<ErrorsWrapper, AddCommentBffOutput> process(AddCommentBffInput input) {
        return Try.of(() -> addComment(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private AddCommentBffOutput addComment(AddCommentBffInput input) {
        log.info("Started addComment with input: {}", input);
        validateInput(input);

        AddCommentInput inputFromComments = conversionService.convert(input, AddCommentInput.class);
        AddCommentOutput outputFromComments = commentsRestClient.addComment(inputFromComments.getRoomId(), inputFromComments);
        AddCommentBffOutput output = conversionService.convert(outputFromComments, AddCommentBffOutput.class);

        log.info("Ended addComment with output: {}", output);
        return output;
    }
}
