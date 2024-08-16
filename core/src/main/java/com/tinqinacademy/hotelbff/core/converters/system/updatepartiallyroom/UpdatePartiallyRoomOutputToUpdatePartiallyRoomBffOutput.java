package com.tinqinacademy.hotelbff.core.converters.system.updatepartiallyroom;

import com.tinqinacademy.hotel.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomOutput;
import com.tinqinacademy.hotelbff.api.operations.system.updatepartiallyroom.UpdatePartiallyRoomBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdatePartiallyRoomOutputToUpdatePartiallyRoomBffOutput implements Converter<UpdatePartiallyRoomOutput, UpdatePartiallyRoomBffOutput> {
    @Override
    public UpdatePartiallyRoomBffOutput convert(UpdatePartiallyRoomOutput source) {
        log.info("Started Converter - UpdatePartiallyRoomOutput to UpdatePartiallyRoomBffOutput");

        UpdatePartiallyRoomBffOutput target = UpdatePartiallyRoomBffOutput.builder()
                .id(source.getId())
                .build();

        log.info("Ended Converter - UpdatePartiallyRoomOutput to UpdatePartiallyRoomBffOutput");
        return target;
    }
}
