package com.tinqinacademy.hotelbff.core.processors.hotel;


import com.tinqinacademy.hotel.api.operations.hotel.getroombasicinfo.GetRoomBasicInfoInput;
import com.tinqinacademy.hotel.api.operations.hotel.getroombasicinfo.GetRoomBasicInfoOutput;
import com.tinqinacademy.hotelbff.api.error.ErrorsWrapper;
import com.tinqinacademy.hotelbff.api.operations.hotel.getroombasicinfo.GetRoomBasicInfoBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getroombasicinfo.GetRoomBasicInfoBffOperation;
import com.tinqinacademy.hotelbff.api.operations.hotel.getroombasicinfo.GetRoomBasicInfoBffOutput;
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
public class GetRoomBasicInfoOperationProcessor extends BaseOperationProcessor<GetRoomBasicInfoBffInput> implements GetRoomBasicInfoBffOperation {

    private final HotelRestClient hotelRestClient;

    protected GetRoomBasicInfoOperationProcessor(ConversionService conversionService, ErrorHandler errorHandler, Validator validator, HotelRestClient hotelRestClient) {
        super(conversionService, errorHandler, validator);
        this.hotelRestClient = hotelRestClient;
    }

    @Override
    public Either<ErrorsWrapper, GetRoomBasicInfoBffOutput> process(GetRoomBasicInfoBffInput input) {
        return Try.of(() -> getRoomBasicInfo(input))
                .toEither()
                .mapLeft(errorHandler::handleErrors);
    }

    private GetRoomBasicInfoBffOutput getRoomBasicInfo(GetRoomBasicInfoBffInput input) {
        log.info("Started getRoomBasicInfo with input: {}", input);
        validateInput(input);

        //not necessary
        GetRoomBasicInfoInput inputFromHotel = conversionService.convert(input, GetRoomBasicInfoInput.class);
        GetRoomBasicInfoOutput outputFromHotel = hotelRestClient.getRoomBasicInfo(inputFromHotel.getRoomId());
        GetRoomBasicInfoBffOutput output = conversionService.convert(outputFromHotel, GetRoomBasicInfoBffOutput.class);

        log.info("Ended getRoomBasicInfo with output: {}", output);
        return output;
    }
}
