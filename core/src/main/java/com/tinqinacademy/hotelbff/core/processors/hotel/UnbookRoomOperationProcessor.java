package com.tinqinacademy.hotelbff.core.processors.hotel;

import com.tinqinacademy.hotel.api.operations.hotel.unbookroom.UnbookRoomInput;
import com.tinqinacademy.hotel.api.operations.hotel.unbookroom.UnbookRoomOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.hotel.unbookroom.UnbookRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.unbookroom.UnbookRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.unbookroom.UnbookRoomBffOutput;
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
public class UnbookRoomOperationProcessor extends BaseOperationProcessor<UnbookRoomBffInput> implements UnbookRoomBffOperation {

    private final HotelRestClient hotelRestClient;

    protected UnbookRoomOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, HotelRestClient hotelRestClient) {
        super(conversionService, errorHandler, validator);
        this.hotelRestClient = hotelRestClient;
    }

    @Override
    public Either<ErrorsWrapper, UnbookRoomBffOutput> process(UnbookRoomBffInput input) {
        return Try.of(() -> unbookRoom(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private UnbookRoomBffOutput unbookRoom(UnbookRoomBffInput input) {
        log.info("Started unbookRoom with input: {}", input);
        validateInput(input);

        UnbookRoomInput inputFromHotel = conversionService.convert(input, UnbookRoomInput.class);
        UnbookRoomOutput outputFromHotel = hotelRestClient.unbookRoom(input.getBookingId(), inputFromHotel);
        UnbookRoomBffOutput output = conversionService.convert(outputFromHotel, UnbookRoomBffOutput.class);

        log.info("Ended unbookRoom with output: {}", output);
        return output;
    }
}
