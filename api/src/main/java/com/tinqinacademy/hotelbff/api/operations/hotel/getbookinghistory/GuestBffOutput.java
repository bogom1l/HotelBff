package com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GuestBffOutput {
    private String firstName;

    private String lastName;

    private String phoneNumber;

    private LocalDate birthdate;
}
