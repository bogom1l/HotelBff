package com.tinqinacademy.hotelbff.core.processors.system;

import com.tinqinacademy.hotel.api.operations.system.createroom.CreateRoomInput;
import com.tinqinacademy.hotel.api.operations.system.createroom.CreateRoomOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.system.createroom.CreateRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.createroom.CreateRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.system.createroom.CreateRoomBffOutput;
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
public class CreateRoomOperationProcessor extends BaseOperationProcessor<CreateRoomBffInput> implements CreateRoomBffOperation {
    private final HotelRestClient hotelRestClient;

    protected CreateRoomOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, HotelRestClient hotelRestClient) {
        super(conversionService, errorHandler, validator);
        this.hotelRestClient = hotelRestClient;
    }

    @Override
    public Either<ErrorsWrapper, CreateRoomBffOutput> process(CreateRoomBffInput input) {
        return Try.of(() -> createRoom(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private CreateRoomBffOutput createRoom(CreateRoomBffInput input) {
        log.info("Started createRoom with input: {}", input);
        validateInput(input);

        CreateRoomInput inputFromHotel = conversionService.convert(input, CreateRoomInput.class);
        CreateRoomOutput outputFromHotel = hotelRestClient.createRoom(inputFromHotel);
        CreateRoomBffOutput output = conversionService.convert(outputFromHotel, CreateRoomBffOutput.class);

        log.info("Ended createRoom with output: {}", output);
        return output;
    }


}
