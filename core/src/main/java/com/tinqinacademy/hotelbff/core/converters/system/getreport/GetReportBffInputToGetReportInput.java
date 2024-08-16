package com.tinqinacademy.hotelbff.core.converters.system.getreport;

import com.tinqinacademy.hotel.api.operations.system.getreport.GetReportInput;
import com.tinqinacademy.hotelbff.api.operations.system.getreport.GetReportBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetReportBffInputToGetReportInput implements Converter<GetReportBffInput, GetReportInput> {
    @Override
    public GetReportInput convert(GetReportBffInput source) {
        log.info("Started Converter - GetReportBffInput to GetReportInput");

        GetReportInput target = GetReportInput.builder()
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .phoneNumber(source.getPhoneNumber())
                .idCardNumber(source.getIdCardNumber())
                .idCardValidity(source.getIdCardValidity())
                .idCardIssueAuthority(source.getIdCardIssueAuthority())
                .idCardIssueDate(source.getIdCardIssueDate())
                .roomNumber(source.getRoomNumber())
                .build();

        log.info("Ended Converter - GetReportBffInput to GetReportInput");
        return target;
    }
}
