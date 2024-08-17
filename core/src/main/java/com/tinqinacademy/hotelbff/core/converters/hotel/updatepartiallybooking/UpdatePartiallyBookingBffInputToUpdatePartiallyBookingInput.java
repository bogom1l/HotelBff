package com.tinqinacademy.hotelbff.core.converters.hotel.updatepartiallybooking;

import com.tinqinacademy.hotel.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingInput;
import com.tinqinacademy.hotel.api.operations.hotel.updatepartiallybooking.UpdatePartiallyGuestInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.updatepartiallybooking.UpdatePartiallyBookingBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.updatepartiallybooking.UpdatePartiallyGuestBffInput;
import com.tinqinacademy.hotelbff.api.operations.system.registerguest.GuestBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UpdatePartiallyBookingBffInputToUpdatePartiallyBookingInput implements Converter<UpdatePartiallyBookingBffInput, UpdatePartiallyBookingInput> {
    private final ConversionService conversionService;

    @Lazy
    public UpdatePartiallyBookingBffInputToUpdatePartiallyBookingInput(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public UpdatePartiallyBookingInput convert(UpdatePartiallyBookingBffInput source) {
        log.info("Started Converter - UpdatePartiallyBookingBffInput to UpdatePartiallyBookingInput");



        UpdatePartiallyBookingInput target = UpdatePartiallyBookingInput.builder()
                .bookingId(source.getBookingId())
                .roomNumber(source.getRoomNumber())
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .totalPrice(source.getTotalPrice())
                .build();

        if (source.getGuests() != null) {
            List<UpdatePartiallyGuestInput> guests = new ArrayList<>();

            for(UpdatePartiallyGuestBffInput guestBffInput : source.getGuests()) {
                try {
                    UpdatePartiallyGuestInput guest = conversionService.convert(guestBffInput, UpdatePartiallyGuestInput.class);
                    guests.add(guest);
                } catch (Exception e) {
                    log.error("Error converting guest: {}", guestBffInput, e);
                }
            }

            target.setGuests(guests);
        }

        log.info("Ended Converter - UpdatePartiallyBookingBffInput to UpdatePartiallyBookingInput");
        return target;
    }
}
