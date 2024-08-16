package com.tinqinacademy.hotelbff.core.converters.system.registerguest;

import com.tinqinacademy.hotel.api.operations.system.registerguest.GuestInput;
import com.tinqinacademy.hotelbff.api.operations.system.registerguest.GuestBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GuestBffInputToGuestInput implements Converter<GuestBffInput, GuestInput> {
    @Override
    public GuestInput convert(GuestBffInput source) {
        log.info("Started Converter - GuestBffInput to GuestInput");

        GuestInput output = GuestInput.builder()
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .phoneNumber(source.getPhoneNumber())
                .birthdate(source.getBirthdate())
                .idCardIssueAuthority(source.getIdCardIssueAuthority())
                .idCardNumber(source.getIdCardNumber())
                .idCardIssueDate(source.getIdCardIssueDate())
                .idCardValidity(source.getIdCardValidity())
                .build();

        log.info("Ended Converter - GuestBffInput to GuestInput");
        return output;
    }
}
