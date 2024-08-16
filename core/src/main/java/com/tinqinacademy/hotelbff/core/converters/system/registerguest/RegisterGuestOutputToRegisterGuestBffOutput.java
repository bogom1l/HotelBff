package com.tinqinacademy.hotelbff.core.converters.system.registerguest;

import com.tinqinacademy.hotel.api.operations.system.registerguest.RegisterGuestOutput;
import com.tinqinacademy.hotelbff.api.operations.system.registerguest.RegisterGuestBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RegisterGuestOutputToRegisterGuestBffOutput implements Converter<RegisterGuestOutput, RegisterGuestBffOutput> {
    @Override
    public RegisterGuestBffOutput convert(RegisterGuestOutput source) {
        log.info("Started Converter - RegisterGuestOutput to RegisterGuestBffOutput");

        RegisterGuestBffOutput output = RegisterGuestBffOutput.builder().build();

        log.info("Ended Converter - RegisterGuestOutput to RegisterGuestBffOutput");
        return output;
    }
}
