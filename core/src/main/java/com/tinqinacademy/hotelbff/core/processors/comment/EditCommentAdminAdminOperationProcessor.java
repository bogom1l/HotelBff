package com.tinqinacademy.hotelbff.core.processors.comment;

import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminInput;
import com.tinqinacademy.comments.api.operations.editcommentadmin.EditCommentAdminOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.comment.editcommentadmin.EditCommentAdminBffInput;
import com.tinqinacademy.hotelbff.api.operations.comment.editcommentadmin.EditCommentAdminBffOperation;
import com.tinqinacademy.hotelbff.api.operations.comment.editcommentadmin.EditCommentAdminBffOutput;
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
public class EditCommentAdminAdminOperationProcessor extends BaseOperationProcessor<EditCommentAdminBffInput> implements EditCommentAdminBffOperation {

    private final CommentsRestClient commentsRestClient;

    protected EditCommentAdminAdminOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, CommentsRestClient commentsRestClient) {
        super(conversionService, errorHandler, validator);
        this.commentsRestClient = commentsRestClient;
    }

    @Override
    public Either<ErrorsWrapper, EditCommentAdminBffOutput> process(EditCommentAdminBffInput input) {
        return Try.of(() -> editCommentAdmin(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private EditCommentAdminBffOutput editCommentAdmin(EditCommentAdminBffInput input) {
        log.info("Started editCommentAdmin with input: {}", input);

        EditCommentAdminInput inputFromComments = conversionService.convert(input, EditCommentAdminInput.class);
        EditCommentAdminOutput outputFromComments = commentsRestClient.editCommentAdmin(inputFromComments.getCommentId(), inputFromComments);
        EditCommentAdminBffOutput output = conversionService.convert(outputFromComments, EditCommentAdminBffOutput.class);

        log.info("Ended editCommentAdmin with input: {}", output);
        return output;
    }
}
