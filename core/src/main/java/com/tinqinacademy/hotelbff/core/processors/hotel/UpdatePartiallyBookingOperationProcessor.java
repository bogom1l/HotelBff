package com.tinqinacademy.hotelbff.core.processors.hotel;

import com.tinqinacademy.hotel.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingInput;
import com.tinqinacademy.hotel.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingBffOutput;
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
public class UpdatePartiallyBookingOperationProcessor extends BaseOperationProcessor<UpdatePartiallyBookingBffInput> implements UpdatePartiallyBookingBffOperation {
    private final HotelRestClient hotelRestClient;

    protected UpdatePartiallyBookingOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, HotelRestClient hotelRestClient) {
        super(conversionService, errorHandler, validator);
        this.hotelRestClient = hotelRestClient;
    }

    @Override
    public Either<ErrorsWrapper, UpdatePartiallyBookingBffOutput> process(UpdatePartiallyBookingBffInput input) {
        return Try.of(() -> updatePartiallyBooking(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private UpdatePartiallyBookingBffOutput updatePartiallyBooking(UpdatePartiallyBookingBffInput input) {
        log.info("Started updatePartiallyBooking with input: {}", input);

        UpdatePartiallyBookingInput inputfromHotel = conversionService.convert(input, UpdatePartiallyBookingInput.class);
        UpdatePartiallyBookingOutput outputFromHotel = hotelRestClient.updatePartiallyBooking(inputfromHotel.getBookingId(), inputfromHotel);
        UpdatePartiallyBookingBffOutput output = conversionService.convert(outputFromHotel, UpdatePartiallyBookingBffOutput.class);

        log.info("Ended updatePartiallyBooking with output: {}", output);
        return output;
    }
}
