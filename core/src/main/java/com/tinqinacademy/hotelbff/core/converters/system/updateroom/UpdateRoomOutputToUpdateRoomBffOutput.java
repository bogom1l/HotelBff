package com.tinqinacademy.hotelbff.core.converters.system.updateroom;

import com.tinqinacademy.hotel.api.operations.system.updateroom.UpdateRoomOutput;
import com.tinqinacademy.hotelbff.api.operations.system.updateroom.UpdateRoomBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdateRoomOutputToUpdateRoomBffOutput implements Converter<UpdateRoomOutput, UpdateRoomBffOutput> {
    @Override
    public UpdateRoomBffOutput convert(UpdateRoomOutput source) {
        log.info("Started Converter - UpdateRoomOutput to UpdateRoomBffOutput");

        UpdateRoomBffOutput target = UpdateRoomBffOutput.builder()
                .id(source.getId())
                .build();

        log.info("Ended Converter - UpdateRoomOutput to UpdateRoomBffOutput");
        return target;
    }
}
