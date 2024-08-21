package com.tinqinacademy.hotelbff.api.exceptions;

import org.springframework.http.HttpStatus;

public class HotelBffException extends RuntimeException {
    private final HttpStatus status;

    public HotelBffException(String message) {
        super(message);
        this.status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HotelBffException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}