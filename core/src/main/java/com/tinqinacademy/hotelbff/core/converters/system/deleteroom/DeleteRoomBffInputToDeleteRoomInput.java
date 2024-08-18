package com.tinqinacademy.hotelbff.core.converters.system.deleteroom;

import com.tinqinacademy.hotel.api.operations.system.deleteroom.DeleteRoomInput;
import com.tinqinacademy.hotelbff.api.operations.system.deleteroom.DeleteRoomBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeleteRoomBffInputToDeleteRoomInput implements Converter<DeleteRoomBffInput, DeleteRoomInput> {
    @Override
    public DeleteRoomInput convert(DeleteRoomBffInput source) {
        log.info("Started Converter - DeleteRoomBffInput to DeleteRoomInput");

        DeleteRoomInput target = DeleteRoomInput.builder()
                .id(source.getId())
                .build();

        log.info("Ended Converter - DeleteRoomBffInput to DeleteRoomInput");
        return target;
    }
}
