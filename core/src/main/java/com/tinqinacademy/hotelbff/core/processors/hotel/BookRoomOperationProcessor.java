package com.tinqinacademy.hotelbff.core.processors.hotel;

import com.tinqinacademy.hotel.api.operations.hotel.bookroom.BookRoomInput;
import com.tinqinacademy.hotel.api.operations.hotel.bookroom.BookRoomOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.hotel.bookroom.BookRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.bookroom.BookRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.bookroom.BookRoomBffOutput;
import com.tinqinacademy.hotelbff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.hotelbff.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.hotelbff.domain.feignclients.HotelRestClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookRoomOperationProcessor extends BaseOperationProcessor<BookRoomBffInput> implements BookRoomBffOperation {

    private final HotelRestClient hotelRestClient;

    protected BookRoomOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, HotelRestClient hotelRestClient) {
        super(conversionService, errorHandler, validator);
        this.hotelRestClient = hotelRestClient;
    }

    @Override
    public Either<ErrorsWrapper, BookRoomBffOutput> process(BookRoomBffInput input) {
        return Try.of(() -> bookRoom(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private BookRoomBffOutput bookRoom(BookRoomBffInput input) {
        log.info("Started bookRoom with input: {}", input);
        validateInput(input);

        BookRoomInput inputFromHotel = conversionService.convert(input, BookRoomInput.class);
        BookRoomOutput outputFromHotel = hotelRestClient.bookRoom(input.getRoomId(), inputFromHotel);
        BookRoomBffOutput output = conversionService.convert(outputFromHotel, BookRoomBffOutput.class);

        log.info("Ended bookRoom with output: {}", output);
        return output;
    }
}
