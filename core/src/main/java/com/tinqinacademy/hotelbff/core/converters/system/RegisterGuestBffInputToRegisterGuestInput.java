package com.tinqinacademy.hotelbff.core.converters.system;

import com.tinqinacademy.hotel.api.operations.system.registerguest.GuestInput;
import com.tinqinacademy.hotel.api.operations.system.registerguest.RegisterGuestInput;
import com.tinqinacademy.hotelbff.api.operations.registerguest.RegisterGuestBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class RegisterGuestBffInputToRegisterGuestInput implements Converter<RegisterGuestBffInput, RegisterGuestInput> {

    private final ConversionService conversionService;

    @Lazy
    public RegisterGuestBffInputToRegisterGuestInput(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public RegisterGuestInput convert(RegisterGuestBffInput source) {
        log.info("Started Converter - RegisterGuestBffInput to RegisterGuestInput");

        List<GuestInput> guests = source.getGuests().stream()
                .map(guest -> conversionService.convert(guest, GuestInput.class))
                .toList();

        RegisterGuestInput output = RegisterGuestInput.builder()
                .guests(guests)
                .roomId(source.getRoomId())
                .build();

        log.info("Ended Converter - RegisterGuestBffInput to RegisterGuestInput");
        return output;
    }
}
