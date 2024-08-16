package com.tinqinacademy.hotelbff.core.converters.system;

import com.tinqinacademy.hotel.api.operations.system.createroom.CreateRoomOutput;
import com.tinqinacademy.hotelbff.api.operations.system.createroom.CreateRoomBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateRoomOutputToCreateRoomBffOutput implements Converter<CreateRoomOutput, CreateRoomBffOutput> {
    @Override
    public CreateRoomBffOutput convert(CreateRoomOutput source) {
        log.info("Started Converter - CreateRoomOutput to CreateRoomBffOutput");
        CreateRoomBffOutput target = CreateRoomBffOutput.builder()
                .id(source.getId())
                .build();

        log.info("Ended Converter - CreateRoomOutput to CreateRoomBffOutput");
        return target;
    }
}
