package com.tinqinacademy.hotelbff.core.converters.system;

import com.tinqinacademy.hotel.api.operations.system.getreport.GetReportOutput;
import com.tinqinacademy.hotelbff.api.operations.system.getreport.GetReportBffOutput;
import com.tinqinacademy.hotelbff.api.operations.system.getreport.GuestBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class GetReportOutputToGetReportBffOutput implements Converter<GetReportOutput, GetReportBffOutput> {

    private final ConversionService conversionService;

    @Lazy
    public GetReportOutputToGetReportBffOutput(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public GetReportBffOutput convert(GetReportOutput source) {
        log.info("Started Converter - GetReportOutput to GetReportBffOutput");

        List<GuestBffOutput> guestBffOutputList = source.getGuests().stream()
                .map(guest -> conversionService.convert(guest, GuestBffOutput.class))
                .toList();

        GetReportBffOutput target = GetReportBffOutput.builder()
                .guests(guestBffOutputList)
                .build();

        log.info("Ended Converter - GetReportOutput to GetReportBffOutput");
        return target;
    }
}
