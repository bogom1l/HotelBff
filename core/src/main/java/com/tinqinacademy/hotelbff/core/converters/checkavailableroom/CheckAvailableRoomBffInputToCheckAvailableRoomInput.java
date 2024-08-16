package com.tinqinacademy.hotelbff.core.converters.checkavailableroom;

import com.tinqinacademy.hotel.api.operations.hotel.checkavailableroom.CheckAvailableRoomInput;
import com.tinqinacademy.hotelbff.api.operations.checkavailableroom.CheckAvailableRoomBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CheckAvailableRoomBffInputToCheckAvailableRoomInput implements Converter<CheckAvailableRoomBffInput, CheckAvailableRoomInput> {

    @Override
    public CheckAvailableRoomInput convert(CheckAvailableRoomBffInput source) {
        log.info("Started Converter - CheckAvailableRoomBffInput to CheckAvailableRoomInput");

        CheckAvailableRoomInput output = CheckAvailableRoomInput.builder()
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .bedSize(source.getBedSize())
                .bathroomType(source.getBathroomType())
                .build();

        log.info("Ended Converter - CheckAvailableRoomBffInput to CheckAvailableRoomInput");
        return output;
    }
}
