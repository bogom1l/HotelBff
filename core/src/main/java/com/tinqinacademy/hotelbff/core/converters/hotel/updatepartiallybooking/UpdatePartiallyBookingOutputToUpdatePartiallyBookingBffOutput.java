package com.tinqinacademy.hotelbff.core.converters.hotel.updatepartiallybooking;

import com.tinqinacademy.hotel.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingOutput;
import com.tinqinacademy.hotelbff.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Slf4j
public class UpdatePartiallyBookingOutputToUpdatePartiallyBookingBffOutput implements Converter<UpdatePartiallyBookingOutput, UpdatePartiallyBookingBffOutput> {
    @Override
    public UpdatePartiallyBookingBffOutput convert(UpdatePartiallyBookingOutput source) {
        log.info("Started Converter - UpdatePartiallyBookingOutput to UpdatePartiallyBookingBffOutput");

        UpdatePartiallyBookingBffOutput target = UpdatePartiallyBookingBffOutput.builder()
                .id(source.getId())
                .build();

        log.info("Ended Converter - UpdatePartiallyBookingOutput to UpdatePartiallyBookingBffOutput");
        return target;
    }
}
