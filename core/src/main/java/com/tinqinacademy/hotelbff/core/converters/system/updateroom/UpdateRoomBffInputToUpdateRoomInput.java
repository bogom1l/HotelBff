package com.tinqinacademy.hotelbff.core.converters.system.updateroom;

import com.tinqinacademy.hotel.api.operations.system.updateroom.UpdateRoomInput;
import com.tinqinacademy.hotelbff.api.operations.system.updateroom.UpdateRoomBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdateRoomBffInputToUpdateRoomInput implements Converter<UpdateRoomBffInput, UpdateRoomInput> {
    @Override
    public UpdateRoomInput convert(UpdateRoomBffInput source) {
        log.info("Started Converter - UpdateRoomBffInput to UpdateRoomInput");

        UpdateRoomInput target = UpdateRoomInput.builder()
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
