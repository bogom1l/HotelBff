package com.tinqinacademy.hotelbff.api.operations.registerguest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class GuestBffInput {
    @NotNull(message = "Start date is mandatory")
    private LocalDate startDate;

    @NotNull(message = "End date is mandatory")
    private LocalDate endDate;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Phone number is mandatory")
    private String phoneNumber;

    @NotBlank(message = "Id card number is mandatory")
    private String idCardNumber;

    @NotNull(message = "Id card validity date is mandatory")
    private LocalDate idCardValidity;

    @NotBlank(message = "Id card issue authority is mandatory")
    private String idCardIssueAuthority;

    @NotNull(message = "Id card issue date is mandatory")
    private LocalDate idCardIssueDate;

    @NotNull(message = "Birth date is mandatory")
    private LocalDate birthdate;
}
