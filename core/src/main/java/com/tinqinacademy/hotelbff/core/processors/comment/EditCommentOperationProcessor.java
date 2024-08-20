package com.tinqinacademy.hotelbff.core.processors.comment;

import com.tinqinacademy.comments.api.operations.editcomment.EditCommentInput;
import com.tinqinacademy.comments.api.operations.editcomment.EditCommentOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.comment.editcomment.EditCommentBffInput;
import com.tinqinacademy.hotelbff.api.operations.comment.editcomment.EditCommentBffOperation;
import com.tinqinacademy.hotelbff.api.operations.comment.editcomment.EditCommentBffOutput;
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
public class EditCommentOperationProcessor extends BaseOperationProcessor<EditCommentBffInput> implements EditCommentBffOperation {
    private final CommentsRestClient commentsRestClient;

    protected EditCommentOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, CommentsRestClient commentsRestClient) {
        super(conversionService, errorHandler, validator);
        this.commentsRestClient = commentsRestClient;
    }

    @Override
    public Either<ErrorsWrapper, EditCommentBffOutput> process(EditCommentBffInput input) {
        return Try.of(() -> editComment(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private EditCommentBffOutput editComment(EditCommentBffInput input) {
        log.info("Started editComment with input: {}", input);

        EditCommentInput inputFromComments = conversionService.convert(input, EditCommentInput.class);
        EditCommentOutput outputFromComments = commentsRestClient.editComment(inputFromComments.getCommentId(), inputFromComments);
        EditCommentBffOutput output = conversionService.convert(outputFromComments, EditCommentBffOutput.class);

        log.info("Ended editComment with output: {}", output);
        return output;
    }
}
