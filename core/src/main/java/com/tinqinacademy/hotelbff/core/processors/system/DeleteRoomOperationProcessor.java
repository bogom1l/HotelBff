package com.tinqinacademy.hotelbff.core.processors.system;

import com.tinqinacademy.hotel.api.operations.system.deleteroom.DeleteRoomInput;
import com.tinqinacademy.hotel.api.operations.system.deleteroom.DeleteRoomOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.system.deleteroom.DeleteRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.deleteroom.DeleteRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.system.deleteroom.DeleteRoomBffOutput;
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
public class DeleteRoomOperationProcessor extends BaseOperationProcessor<DeleteRoomBffInput> implements DeleteRoomBffOperation {
    private final HotelRestClient hotelRestClient;

    protected DeleteRoomOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, HotelRestClient hotelRestClient) {
        super(conversionService, errorHandler, validator);
        this.hotelRestClient = hotelRestClient;
    }

    @Override
    public Either<ErrorsWrapper, DeleteRoomBffOutput> process(DeleteRoomBffInput input) {
        return Try.of(() -> deleteRoom(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private DeleteRoomBffOutput deleteRoom(DeleteRoomBffInput input) {
        log.info("Started deleteRoom with input: {}", input);
        validateInput(input);

        DeleteRoomInput inputFromHotel = conversionService.convert(input, DeleteRoomInput.class);
        DeleteRoomOutput outputFromHotel = hotelRestClient.deleteRoom(inputFromHotel.getId());
        DeleteRoomBffOutput output = conversionService.convert(outputFromHotel, DeleteRoomBffOutput.class);

        log.info("Ended deleteRoom with output: {}", output);
        return output;
    }
}
