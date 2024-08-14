package com.tinqinacademy.hotelbff.core.converters;

import com.tinqinacademy.hotel.api.operations.hotel.getroombasicinfo.GetRoomBasicInfoOutput;
import com.tinqinacademy.hotelbff.api.enums.BathroomType;
import com.tinqinacademy.hotelbff.api.enums.BedSize;
import com.tinqinacademy.hotelbff.api.operations.getroombasicinfo.GetRoomBasicInfoBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetRoomBasicInfoOutputToGetRoomBasicInfoBffOutput implements Converter<GetRoomBasicInfoOutput, GetRoomBasicInfoBffOutput> {
    @Override
    public GetRoomBasicInfoBffOutput convert(GetRoomBasicInfoOutput source) {
        log.info("Started Converter - GetRoomBasicInfoOutput to GetRoomBasicInfoBffOutput");

        GetRoomBasicInfoBffOutput output = GetRoomBasicInfoBffOutput.builder()
                .id(source.getId())
                .price(source.getPrice())
                .floor(source.getFloor())
                .bedSize(BedSize.getByCode(source.getBedSize().toString()))
                .bathroomType(BathroomType.getByCode(source.getBedSize().toString()))
                .datesOccupied(source.getDatesOccupied())
                .roomNumber(source.getRoomNumber())
                .build();

        log.info("Ended Converter - GetRoomBasicInfoOutput to GetRoomBasicInfoBffOutput");
        return output;
    }
}
