package com.tinqinacademy.hotelbff.core.converters.system.getreport;

import com.tinqinacademy.hotel.api.operations.system.getreport.GuestOutput;
import com.tinqinacademy.hotelbff.api.operations.system.getreport.GuestBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GuestOutputToGuestBffOutput implements Converter<GuestOutput, GuestBffOutput> {
    @Override
    public GuestBffOutput convert(GuestOutput source) {
        log.info("Started Converter - GuestOutput to GuestBffOutput");

        GuestBffOutput target = GuestBffOutput.builder()
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .phoneNumber(source.getPhoneNumber())
                .idCardNumber(source.getIdCardNumber())
                .idCardValidity(source.getIdCardValidity())
                .idCardIssueAuthority(source.getIdCardIssueAuthority())
                .idCardIssueDate(source.getIdCardIssueDate())
                .build();

        log.info("Ended Converter - GuestOutput to GuestBffOutput");
        return target;
    }
}
