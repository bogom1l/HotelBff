package com.tinqinacademy.hotelbff.core.converters.hotel.getbookinghistory;

import com.tinqinacademy.hotel.api.operations.hotel.getbookinghistory.GetBookingHistoryRoomOutput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.RoomBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetBookingHistoryRoomOutputToRoomBffOutput implements Converter<GetBookingHistoryRoomOutput, RoomBffOutput> {
    @Override
    public RoomBffOutput convert(GetBookingHistoryRoomOutput source) {
        log.info("Started Converter - GetBookingHistoryRoomOutput to RoomBffOutput");

        RoomBffOutput target = RoomBffOutput.builder()
                .roomNumber(source.getRoomNumber())
                .roomPrice(source.getRoomPrice())
                .roomFloor(source.getRoomFloor())
                .build();

        log.info("Ended Converter - GetBookingHistoryRoomOutput to RoomBffOutput");
        return target;
    }
}
