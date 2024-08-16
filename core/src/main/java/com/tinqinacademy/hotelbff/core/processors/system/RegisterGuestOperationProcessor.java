package com.tinqinacademy.hotelbff.core.processors.system;

import com.tinqinacademy.hotel.api.operations.system.registerguest.RegisterGuestInput;
import com.tinqinacademy.hotel.api.operations.system.registerguest.RegisterGuestOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.system.registerguest.RegisterGuestBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.registerguest.RegisterGuestBffOperation;
import com.tinqinacademy.hotelbff.api.operations.system.registerguest.RegisterGuestBffOutput;
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
public class RegisterGuestOperationProcessor extends BaseOperationProcessor<RegisterGuestBffInput> implements RegisterGuestBffOperation {
    private final HotelRestClient hotelRestClient;

    protected RegisterGuestOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, HotelRestClient hotelRestClient) {
        super(conversionService, errorHandler, validator);
        this.hotelRestClient = hotelRestClient;
    }

    @Override
    public Either<ErrorsWrapper, RegisterGuestBffOutput> process(RegisterGuestBffInput input) {
        return Try.of(() -> registerGuest(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private RegisterGuestBffOutput registerGuest(RegisterGuestBffInput input) {
        log.info("Started registerGuest with input: {}", input);
        validateInput(input);

        RegisterGuestInput inputFromHotel = conversionService.convert(input, RegisterGuestInput.class);
        RegisterGuestOutput outputFromHotel = hotelRestClient.registerGuest(inputFromHotel);
        RegisterGuestBffOutput output = conversionService.convert(outputFromHotel, RegisterGuestBffOutput.class);

        log.info("Ended registerGuest with output: {}", output);
        return output;
    }
}
