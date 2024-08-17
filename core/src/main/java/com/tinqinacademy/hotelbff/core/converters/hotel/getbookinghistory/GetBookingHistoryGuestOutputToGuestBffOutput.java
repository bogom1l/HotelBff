package com.tinqinacademy.hotelbff.core.converters.hotel.getbookinghistory;

import com.tinqinacademy.hotel.api.operations.hotel.getbookinghistory.GetBookingHistoryGuestOutput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.GuestBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetBookingHistoryGuestOutputToGuestBffOutput implements Converter<GetBookingHistoryGuestOutput, GuestBffOutput> {
    @Override
    public GuestBffOutput convert(GetBookingHistoryGuestOutput source) {
        log.info("Started Converter - GetBookingHistoryGuestOutput to GuestBffOutput");

        GuestBffOutput target = GuestBffOutput.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .phoneNumber(source.getPhoneNumber())
                .birthdate(source.getBirthdate())
                .build();

        log.info("Ended Converter - GetBookingHistoryGuestOutput to GuestBffOutput");
        return target;
    }
}
