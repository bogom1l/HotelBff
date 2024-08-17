package com.tinqinacademy.hotelbff.core.converters.hotel.getroombasicinfo;

import com.tinqinacademy.hotel.api.operations.hotel.getroombasicinfo.GetRoomBasicInfoInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getroombasicinfo.GetRoomBasicInfoBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetRoomBasicInfoBffInputToGetRoomBasicInfoInput implements Converter<GetRoomBasicInfoBffInput, GetRoomBasicInfoInput> {
    @Override
    public GetRoomBasicInfoInput convert(GetRoomBasicInfoBffInput source) {
        log.info("Started Converter - GetRoomBasicInfoBffInput to GetRoomBasicInfoInput");

        GetRoomBasicInfoInput target = GetRoomBasicInfoInput.builder()
                .roomId(source.getRoomId())
                .build();

        log.info("Ended Converter - GetRoomBasicInfoBffInput to GetRoomBasicInfoInput");
        return target;
    }
}
