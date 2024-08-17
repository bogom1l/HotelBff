package com.tinqinacademy.hotelbff.core.converters.system.deleteroom;

import com.tinqinacademy.hotel.api.operations.system.deleteroom.DeleteRoomOutput;
import com.tinqinacademy.hotelbff.api.operations.system.deleteroom.DeleteRoomBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DeleteRoomOutputToDeleteRoomBffOutput implements Converter<DeleteRoomOutput, DeleteRoomBffOutput> {
    @Override
    public DeleteRoomBffOutput convert(DeleteRoomOutput source) {
        log.info("Started Converter - DeleteRoomOutput to DeleteRoomBffOutput");

        DeleteRoomBffOutput target = DeleteRoomBffOutput.builder().build();

        log.info("Ended Converter - DeleteRoomOutput to DeleteRoomBffOutput");
        return target;
    }
}
