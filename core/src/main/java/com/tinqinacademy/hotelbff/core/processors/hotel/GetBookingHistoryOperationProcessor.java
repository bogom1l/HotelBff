package com.tinqinacademy.hotelbff.core.processors.hotel;

import com.tinqinacademy.hotel.api.operations.hotel.getbookinghistory.GetBookingHistoryInput;
import com.tinqinacademy.hotel.api.operations.hotel.getbookinghistory.GetBookingHistoryOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.GetBookingHistoryBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.GetBookingHistoryBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.GetBookingHistoryBffOutput;
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
public class GetBookingHistoryOperationProcessor extends BaseOperationProcessor<GetBookingHistoryBffInput> implements GetBookingHistoryBffOperation {
    private final HotelRestClient hotelRestClient;

    protected GetBookingHistoryOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, HotelRestClient hotelRestClient) {
        super(conversionService, errorHandler, validator);
        this.hotelRestClient = hotelRestClient;
    }

    @Override
    public Either<ErrorsWrapper, GetBookingHistoryBffOutput> process(GetBookingHistoryBffInput input) {
        return Try.of(() -> getBookingHistory(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private GetBookingHistoryBffOutput getBookingHistory(GetBookingHistoryBffInput input) {
        log.info("Started getBookingHistory with input: {}", input);
        validateInput(input);

        GetBookingHistoryInput inputFromHotel = conversionService.convert(input, GetBookingHistoryInput.class);
        GetBookingHistoryOutput outputFromHotel = hotelRestClient.getBookingHistory(inputFromHotel.getUserId());
        GetBookingHistoryBffOutput output = conversionService.convert(outputFromHotel, GetBookingHistoryBffOutput.class);

        log.info("Ended getBookingHistory with output: {}", output);
        return output;
    }
}
