package com.tinqinacademy.hotelbff.core.converters.system;

import com.tinqinacademy.hotel.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomInput;
import com.tinqinacademy.hotel.api.operations.system.updateroom.UpdateRoomInput;
import com.tinqinacademy.hotelbff.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.updateroom.UpdateRoomBffInput;
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
