package com.tinqinacademy.hotelbff.api.base;

import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import io.vavr.control.Either;

public interface OperationProcessor<I extends OperationInput, O extends OperationOutput> {
    Either<ErrorsWrapper, O> process(I input);
}
