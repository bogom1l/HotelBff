package com.tinqinacademy.hotelbff.core.converters.system;

import com.tinqinacademy.hotel.api.operations.system.createroom.CreateRoomInput;
import com.tinqinacademy.hotelbff.api.operations.system.createroom.CreateRoomBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateRoomBffInputToCreateRoomInput implements Converter<CreateRoomBffInput, CreateRoomInput> {
    @Override
    public CreateRoomInput convert(CreateRoomBffInput source) {
      log.info("Started Converter - CreateRoomBffInput to CreateRoomInput");

      CreateRoomInput target = CreateRoomInput.builder()
              .bedSize(source.getBedSize())
              .bathroomType(source.getBathroomType())
              .floor(source.getFloor())
              .roomNumber(source.getRoomNumber())
              .price(source.getPrice())
              .build();

        log.info("Ended Converter - CreateRoomBffInput to CreateRoomInput");
        return target;
    }
}
