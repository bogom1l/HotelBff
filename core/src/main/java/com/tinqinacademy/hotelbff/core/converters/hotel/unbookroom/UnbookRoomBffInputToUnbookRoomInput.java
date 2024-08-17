package com.tinqinacademy.hotelbff.core.converters.hotel.unbookroom;

import com.tinqinacademy.hotel.api.operations.hotel.unbookroom.UnbookRoomInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.unbookroom.UnbookRoomBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UnbookRoomBffInputToUnbookRoomInput implements Converter<UnbookRoomBffInput, UnbookRoomInput> {
    @Override
    public UnbookRoomInput convert(UnbookRoomBffInput source) {

        log.info("Started Converter - UnbookRoomBffInput to UnbookRoomInput");

        UnbookRoomInput target = UnbookRoomInput.builder()
                .bookingId(source.getBookingId())
                .userId(source.getUserContextId())
                .build();

        log.info("Ended Converter - UnbookRoomBffInput to UnbookRoomInput");
        return target;
    }
}
