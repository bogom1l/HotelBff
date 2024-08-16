package com.tinqinacademy.hotelbff.core.converters.hotel.checkavailableroom;

import com.tinqinacademy.hotel.api.operations.hotel.checkavailableroom.CheckAvailableRoomOutput;
import com.tinqinacademy.hotelbff.api.operations.checkavailableroom.CheckAvailableRoomBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CheckAvailableRoomOutputToCheckAvailableRoomBffOutput implements Converter<CheckAvailableRoomOutput, CheckAvailableRoomBffOutput> {
    @Override
    public CheckAvailableRoomBffOutput convert(CheckAvailableRoomOutput source) {
        log.info("Started Converter - CheckAvailableRoomOutput to CheckAvailableRoomBffOutput");

        CheckAvailableRoomBffOutput output = CheckAvailableRoomBffOutput.builder()
                .ids(source.getIds())
                .build();

        log.info("Ended Converter - CheckAvailableRoomOutput to CheckAvailableRoomBffOutput");
        return output;
    }
}
