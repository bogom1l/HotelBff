package com.tinqinacademy.hotelbff.core.processors.system;

import com.tinqinacademy.hotel.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomInput;
import com.tinqinacademy.hotel.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomBffOutput;
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
public class UpdatePartiallyRoomOperationProcessor extends BaseOperationProcessor<UpdatePartiallyRoomBffInput> implements UpdatePartiallyRoomBffOperation {
    private final HotelRestClient hotelRestClient;

    protected UpdatePartiallyRoomOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, HotelRestClient hotelRestClient) {
        super(conversionService, errorHandler, validator);
        this.hotelRestClient = hotelRestClient;
    }

    @Override
    public Either<ErrorsWrapper, UpdatePartiallyRoomBffOutput> process(UpdatePartiallyRoomBffInput input) {
        return Try.of( () -> updatePartiallyRoom(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private UpdatePartiallyRoomBffOutput updatePartiallyRoom(UpdatePartiallyRoomBffInput input) {
        log.info("Started updateRoom with input: {}", input);
        validateInput(input);

        UpdatePartiallyRoomInput inputFromHotel = conversionService.convert(input, UpdatePartiallyRoomInput.class);
        UpdatePartiallyRoomOutput outputFromHotel = hotelRestClient.updatePartiallyRoom(inputFromHotel.getRoomId(), inputFromHotel);
        UpdatePartiallyRoomBffOutput output = conversionService.convert(outputFromHotel, UpdatePartiallyRoomBffOutput.class);

        log.info("Ended updateRoom with output: {}", output);
        return output;
    }

}
