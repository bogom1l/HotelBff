package com.tinqinacademy.hotelbff.core.processors.system;

import com.tinqinacademy.hotel.api.operations.system.createroom.CreateRoomInput;
import com.tinqinacademy.hotel.api.operations.system.createroom.CreateRoomOutput;
import com.tinqinacademy.hotel.api.operations.system.updateroom.UpdateRoomInput;
import com.tinqinacademy.hotel.api.operations.system.updateroom.UpdateRoomOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.system.createroom.CreateRoomBffOutput;
import com.tinqinacademy.hotelbff.api.operations.system.updateroom.UpdateRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.updateroom.UpdateRoomBffOperation;
import com.tinqinacademy.hotelbff.api.operations.system.updateroom.UpdateRoomBffOutput;
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
public class UpdateRoomOperationProcessor extends BaseOperationProcessor<UpdateRoomBffInput> implements UpdateRoomBffOperation {
    private final HotelRestClient hotelRestClient;

    protected UpdateRoomOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, HotelRestClient hotelRestClient) {
        super(conversionService, errorHandler, validator);
        this.hotelRestClient = hotelRestClient;
    }

    @Override
    public Either<ErrorsWrapper, UpdateRoomBffOutput> process(UpdateRoomBffInput input) {
        return Try.of( () -> updateRoom(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private UpdateRoomBffOutput updateRoom(UpdateRoomBffInput input) {
        log.info("Started updateRoom with input: {}", input);
        validateInput(input);

        UpdateRoomInput inputFromHotel = conversionService.convert(input, UpdateRoomInput.class);
        UpdateRoomOutput outputFromHotel = hotelRestClient.updateRoom(inputFromHotel.getRoomId(), inputFromHotel);
        UpdateRoomBffOutput output = conversionService.convert(outputFromHotel, UpdateRoomBffOutput.class);

        log.info("Ended updateRoom with output: {}", output);
        return output;
    }
}
