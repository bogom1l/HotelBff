package com.tinqinacademy.hotelbff.core.converters.hotel.updatepartiallybooking;

import com.tinqinacademy.hotel.api.operations.hotel.updatepartiallybooking.UpdatePartiallyGuestInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.updatepartiallybooking.UpdatePartiallyGuestBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdatePartiallyGuestBffInputToUpdatePartiallyGuestInput implements Converter<UpdatePartiallyGuestBffInput, UpdatePartiallyGuestInput> {
    @Override
    public UpdatePartiallyGuestInput convert(UpdatePartiallyGuestBffInput source) {
        log.info("Started Converter - UpdatePartiallyGuestBffInput to UpdatePartiallyGuestInput");

        UpdatePartiallyGuestInput target = UpdatePartiallyGuestInput.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .phoneNumber(source.getPhoneNumber())
                .birthdate(source.getBirthdate())
                .idCardIssueAuthority(source.getIdCardIssueAuthority())
                .idCardIssueDate(source.getIdCardIssueDate())
                .idCardNumber(source.getIdCardNumber())
                .idCardValidity(source.getIdCardValidity())
                .build();

        log.info("Ended Converter - UpdatePartiallyGuestBffInput to UpdatePartiallyGuestInput");
        return target;
    }
}
