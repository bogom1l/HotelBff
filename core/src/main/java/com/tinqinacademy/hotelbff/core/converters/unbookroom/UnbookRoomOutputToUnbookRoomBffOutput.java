package com.tinqinacademy.hotelbff.core.converters.unbookroom;

import com.tinqinacademy.hotel.api.operations.hotel.unbookroom.UnbookRoomOutput;
import com.tinqinacademy.hotelbff.api.operations.deletebooking.UnbookRoomBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UnbookRoomOutputToUnbookRoomBffOutput implements Converter<UnbookRoomOutput, UnbookRoomBffOutput> {
    @Override
    public UnbookRoomBffOutput convert(UnbookRoomOutput source) {
        log.info("Started Converter - UnbookRoomOutput to UnbookRoomBffOutput");

        UnbookRoomBffOutput output = UnbookRoomBffOutput.builder().build();

        log.info("Ended Converter - UnbookRoomOutput to UnbookRoomBffOutput");
        return output;
    }
}
