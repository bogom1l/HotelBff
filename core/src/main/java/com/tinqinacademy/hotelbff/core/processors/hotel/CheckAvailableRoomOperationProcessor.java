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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

//    private CheckAvailableRoomOutput checkAvailableRooms(CheckAvailableRoomBffInput input) {
//        log.info("Started checkAvailableRoom with input: {}", input);
//        validateInput(input);
//
//
//        LocalDate startDate = input.getStartDate();
//        LocalDate endDate = input.getEndDate();
//        BedSize bedSize = input.getBedSize() != null
//                ? BedSize.getByCode(input.getBedSize()) : null;
//        BathroomType bathroomType = input.getBathroomType() != null
//                ? BathroomType.getByCode(input.getBathroomType()) : null;
//
//        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
//            throw new HotelException("Start date should be before end date.");
//        }
//
//        List<Room> availableRooms = roomRepository.findAvailableRoomsBetweenDates(startDate, endDate)
//                .orElseThrow(() -> new HotelException("No available rooms found"));
//
//        List<Room> filteredRooms = availableRooms.stream()
//                .filter(room -> bedSize == null || room.getBeds().stream().anyMatch(bed -> bed.getBedSize().equals(bedSize)))
//                .filter(room -> bathroomType == null || room.getBathroomType().equals(bathroomType))
//                .toList();
//
//        CheckAvailableRoomOutput output = conversionService.convert(filteredRooms, CheckAvailableRoomOutput.class);
//
//        log.info("Ended checkAvailableRoom with output: {}", output);
//        return output;
//    }


}
