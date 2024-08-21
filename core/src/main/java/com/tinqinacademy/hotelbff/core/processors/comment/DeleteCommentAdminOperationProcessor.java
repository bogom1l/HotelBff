package com.tinqinacademy.hotelbff.core.processors.comment;


import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminInput;
import com.tinqinacademy.comments.api.operations.deletecommentadmin.DeleteCommentAdminOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.comment.deletecommentadmin.DeleteCommentAdminBffInput;
import com.tinqinacademy.hotelbff.api.operations.comment.deletecommentadmin.DeleteCommentAdminBffOperation;
import com.tinqinacademy.hotelbff.api.operations.comment.deletecommentadmin.DeleteCommentAdminBffOutput;
import com.tinqinacademy.hotelbff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.hotelbff.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.hotelbff.domain.feignclients.CommentsRestClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DeleteCommentAdminOperationProcessor extends BaseOperationProcessor<DeleteCommentAdminBffInput> implements DeleteCommentAdminBffOperation {

    private static final Logger log = LoggerFactory.getLogger(DeleteCommentAdminOperationProcessor.class);
    private final CommentsRestClient commentsRestClient;

    protected DeleteCommentAdminOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, CommentsRestClient commentsRestClient) {
        super(conversionService, errorHandler, validator);
        this.commentsRestClient = commentsRestClient;
    }

    @Override
    public Either<ErrorsWrapper, DeleteCommentAdminBffOutput> process(DeleteCommentAdminBffInput input) {
        return Try.of(() -> deleteCommentAdmin(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private DeleteCommentAdminBffOutput deleteCommentAdmin(DeleteCommentAdminBffInput input) {
        log.info("Started deleteCommentAdmin with input: {}", input);

        DeleteCommentAdminInput inputFromComments = conversionService.convert(input, DeleteCommentAdminInput.class);
        DeleteCommentAdminOutput outputFromComments = commentsRestClient.deleteCommentAdmin(inputFromComments.getCommentId());
        DeleteCommentAdminBffOutput output = conversionService.convert(outputFromComments, DeleteCommentAdminBffOutput.class);

        log.info("Ended deleteCommentAdmin with output: {}", output);
        return output;
    }
}
