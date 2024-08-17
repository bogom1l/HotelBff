package com.tinqinacademy.hotelbff.core.converters.hotel.getbookinghistory;

import com.tinqinacademy.hotel.api.operations.hotel.getbookinghistory.GetBookingHistoryInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.GetBookingHistoryBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetBookingHistoryBffInputToGetBookingHistoryInput implements Converter<GetBookingHistoryBffInput, GetBookingHistoryInput> {
    @Override
    public GetBookingHistoryInput convert(GetBookingHistoryBffInput input) {
        log.info("Started Converter - GetBookingHistoryBffInput to GetBookingHistoryInput");

        GetBookingHistoryInput target = GetBookingHistoryInput.builder()
                .userId(input.getUserContextId())
                .build();

        log.info("Ended Converter - GetBookingHistoryBffInput to GetBookingHistoryInput");
        return target;
    }
}
