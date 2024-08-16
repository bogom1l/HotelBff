package com.tinqinacademy.hotelbff.core.processors.system;

import com.tinqinacademy.hotel.api.operations.system.getreport.GetReportInput;
import com.tinqinacademy.hotel.api.operations.system.getreport.GetReportOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.exceptions.HotelBffException;
import com.tinqinacademy.hotelbff.api.operations.system.getreport.GetReportBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.getreport.GetReportBffOperation;
import com.tinqinacademy.hotelbff.api.operations.system.getreport.GetReportBffOutput;
import com.tinqinacademy.hotelbff.core.errorhandler.ErrorHandler;
import com.tinqinacademy.hotelbff.core.processors.base.BaseOperationProcessor;
import com.tinqinacademy.hotelbff.domain.feignclients.HotelRestClient;
import io.vavr.control.Either;
import io.vavr.control.Try;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetReportOperationProcessor extends BaseOperationProcessor<GetReportBffInput> implements GetReportBffOperation {
    private final HotelRestClient hotelRestClient;

    protected GetReportOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, HotelRestClient hotelRestClient) {
        super(conversionService, errorHandler, validator);
        this.hotelRestClient = hotelRestClient;
    }

    @Override
    public Either<ErrorsWrapper, GetReportBffOutput> process(GetReportBffInput input) {
        return Try.of(() -> getReport(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private GetReportBffOutput getReport(GetReportBffInput input) {
        log.info("Started getReport with input: {}", input);
        validateInput(input);

        GetReportInput inputFromHotel = conversionService.convert(input, GetReportInput.class);

        GetReportOutput outputFromHotel = hotelRestClient.getReport(
                inputFromHotel.getStartDate(),
                inputFromHotel.getEndDate(),
                inputFromHotel.getFirstName(),
                inputFromHotel.getLastName(),
                inputFromHotel.getPhoneNumber(),
                inputFromHotel.getIdCardNumber(),
                inputFromHotel.getIdCardValidity(),
                inputFromHotel.getIdCardIssueAuthority(),
                inputFromHotel.getIdCardIssueDate(),
                inputFromHotel.getRoomNumber());

        GetReportBffOutput output = conversionService.convert(outputFromHotel, GetReportBffOutput.class);
        
        log.info("Ended getReport with output: {}", output);
        return output;
    }
}
