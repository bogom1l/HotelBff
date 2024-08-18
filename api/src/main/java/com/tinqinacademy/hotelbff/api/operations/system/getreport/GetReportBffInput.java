package com.tinqinacademy.hotelbff.api.operations.system.getreport;

import com.tinqinacademy.hotelbff.api.base.OperationInput;
import com.tinqinacademy.hotelbff.api.validation.room.RoomNumberValidation;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetReportBffInput implements OperationInput {
    //booking
    private String startDate;
    private String endDate;

    //guest
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String idCardNumber;
    private String idCardValidity;
    private String idCardIssueAuthority;
    private String idCardIssueDate;

    //room
    @RoomNumberValidation(optional = true)
    private String roomNumber;
}
