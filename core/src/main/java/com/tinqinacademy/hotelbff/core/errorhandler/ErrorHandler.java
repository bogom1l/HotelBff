package com.tinqinacademy.hotelbff.core.errorhandler;

import com.tinqinacademy.hotelbff.api.error.Error;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.exceptions.HotelBffException;
import com.tinqinacademy.hotelbff.api.exceptions.ValidationException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static io.vavr.API.*;
import static io.vavr.Predicates.instanceOf;

@Component
public class ErrorHandler {
    public ErrorsWrapper handleErrors(Throwable throwable) {
        return Match(throwable).of(
                Case($(instanceOf(MethodArgumentNotValidException.class)), ex -> handleMethodArgumentNotValidException(ex)),
                Case($(instanceOf(ValidationException.class)), ex -> handleValidationException(ex)),
                Case($(instanceOf(HotelBffException.class)), ex -> handleHotelBffException(ex)),
                Case($(instanceOf(FeignException.class)), this::handleFeignException),
                Case($(), ex -> handleGenericException(ex))
        );
    }

    private ErrorsWrapper handleFeignException(FeignException ex) {
        List<Error> errors = new ArrayList<>();
        String message = ex.contentUTF8();
        errors.add(createError(null, message != null && !message.isEmpty() ? message : "An error occurred during the Feign client call."));

        return createErrorsWrapper(errors, HttpStatus.valueOf(ex.status()));
    }

    private static Error createError(String field, String message) {
        return Error.builder()
                .field(field)
                .message(message)
                .build();
    }

    private static ErrorsWrapper createErrorsWrapper(List<Error> errors, HttpStatus status) {
        return ErrorsWrapper.builder()
                .errors(errors)
                .httpStatus(status)
                .build();
    }

    private static ErrorsWrapper handleValidationException(ValidationException ex) {
        List<Error> errors = new ArrayList<>();
        ex.getViolations().forEach(violation -> errors.add(createError(violation.getField(), violation.getMessage())));

        return createErrorsWrapper(errors, HttpStatus.BAD_REQUEST);
    }

    private static ErrorsWrapper handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(createError(error.getField(), error.getDefaultMessage())));

        return createErrorsWrapper(errors, HttpStatus.BAD_REQUEST);
    }

    private static ErrorsWrapper handleHotelBffException(HotelBffException ex) {
        List<Error> errors = List.of(createError(null, ex.getMessage()));
        return createErrorsWrapper(errors, HttpStatus.NOT_FOUND);
    }

    private static ErrorsWrapper handleGenericException(Throwable ex) {
        List<Error> errors = List.of(createError(null, ex.getMessage()));
        return createErrorsWrapper(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}