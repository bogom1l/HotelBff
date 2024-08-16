package com.tinqinacademy.hotelbff.core.converters.system.updatepartiallyroom;

import com.tinqinacademy.hotel.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomInput;
import com.tinqinacademy.hotelbff.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdatePartiallyRoomBffInputToUpdatePartiallyRoomInput implements Converter<UpdatePartiallyRoomBffInput, UpdatePartiallyRoomInput> {
    @Override
    public UpdatePartiallyRoomInput convert(UpdatePartiallyRoomBffInput source) {
        log.info("Started Converter - UpdateRoomBffInput to UpdateRoomInput");

        UpdatePartiallyRoomInput target = UpdatePartiallyRoomInput.builder()
                .bedSize(source.getBedSize())
                .bathroomType(source.getBathroomType())
                .price(source.getPrice())
                .roomNumber(source.getRoomNumber())
                .roomId(source.getRoomId())
                .build();

        log.info("Ended Converter - UpdateRoomBffInput to UpdateRoomInput");
        return target;
    }
}
