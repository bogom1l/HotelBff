package com.tinqinacademy.hotelbff.core.processors.comment;

import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsInput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.comments.getcomments.GetCommentsBffInput;
import com.tinqinacademy.hotelbff.api.operations.comments.getcomments.GetCommentsBffOperation;
import com.tinqinacademy.hotelbff.api.operations.comments.getcomments.GetCommentsBffOutput;
import com.tinqinacademy.hotelbff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.hotelbff.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.hotelbff.domain.feignclients.CommentsRestClient;
import com.tinqinacademy.hotelbff.domain.feignclients.HotelRestClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetAllCommentsOperationProcessor extends BaseOperationProcessor<GetCommentsBffInput> implements GetCommentsBffOperation {
    private final CommentsRestClient commentsRestClient;
    private final HotelRestClient hotelRestClient;

    protected GetAllCommentsOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, CommentsRestClient commentsRestClient, HotelRestClient hotelRestClient) {
        super(conversionService, errorHandler, validator);
        this.commentsRestClient = commentsRestClient;
        this.hotelRestClient = hotelRestClient;
    }

    @Override
    public Either<ErrorsWrapper, GetCommentsBffOutput> process(GetCommentsBffInput input) {
        return Try.of( () -> getComments(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private GetCommentsBffOutput getComments(GetCommentsBffInput input) {
        log.info("Started getComments with input: {}", input);
        validateInput(input);

        // if roomId is invalid, we will get an exception here
        hotelRestClient.getRoomBasicInfo(input.getRoomId());

        GetCommentsInput inputFromComments = conversionService.convert(input, GetCommentsInput.class);
        GetCommentsOutput outputFromComments = commentsRestClient.getAllComments(inputFromComments.getRoomId());
        GetCommentsBffOutput output = conversionService.convert(outputFromComments, GetCommentsBffOutput.class);

        log.info("Ended getComments with output: {}", output);
        return output;
    }
}
