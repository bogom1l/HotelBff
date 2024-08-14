package com.tinqinacademy.hotelbff.core.processors.hotel;

import com.tinqinacademy.hotel.api.operations.hotel.checkavailableroom.CheckAvailableRoomInput;
import com.tinqinacademy.hotel.api.operations.hotel.checkavailableroom.CheckAvailableRoomOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.checkavailableroom.CheckAvailableRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.checkavailableroom.CheckAvailableRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.checkavailableroom.CheckAvailableRoomBffOutput;
import com.tinqinacademy.hotelbff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.hotelbff.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.hotelbff.domain.HotelRestClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CheckAvailableRoomOperationProcessor extends BaseOperationProcessor<CheckAvailableRoomBffInput> implements CheckAvailableRoomBffOperation {

    private final HotelRestClient hotelRestClient;

    protected CheckAvailableRoomOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, HotelRestClient hotelRestClient) {
        super(conversionService, errorHandler, validator);
        this.hotelRestClient = hotelRestClient;
    }

    @Override
    public Either<ErrorsWrapper, CheckAvailableRoomBffOutput> process(CheckAvailableRoomBffInput input) {
        return Try.of(() -> checkAvailableRooms(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private CheckAvailableRoomBffOutput checkAvailableRooms(CheckAvailableRoomBffInput input) {
        log.info("Started checkAvailableRoom with input: {}", input);
        validateInput(input);

        // not necessary to convert CheckAvailableRoomBffInput to CheckAvailableRoomInput
        CheckAvailableRoomInput inputFromHotel = conversionService.convert(input, CheckAvailableRoomInput.class);

        CheckAvailableRoomOutput outputFromHotel = hotelRestClient.checkAvailableRoom(
                inputFromHotel.getStartDate(),
                inputFromHotel.getEndDate(),
                inputFromHotel.getBedSize(),
                inputFromHotel.getBathroomType());

        CheckAvailableRoomBffOutput output = conversionService.convert(outputFromHotel, CheckAvailableRoomBffOutput.class);

        log.info("Ended checkAvailableRoom with output: {}", output);
        return output;
    }

}
