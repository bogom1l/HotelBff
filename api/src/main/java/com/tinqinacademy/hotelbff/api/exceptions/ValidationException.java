package com.tinqinacademy.hotelbff.api.exceptions;

import com.tinqinacademy.hotelbff.api.error.Error;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private final List<Error> violations;

    public ValidationException(List<Error> violations) {
        this.violations = violations;
    }
}