package com.tinqinacademy.hotelbff.api.operations.checkavailableroom;

import com.tinqinacademy.hotelbff.api.base.OperationInput;
import com.tinqinacademy.hotelbff.api.validation.bathroomtype.BathroomTypeValidation;
import com.tinqinacademy.hotelbff.api.validation.bedsize.BedSizeValidation;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CheckAvailableRoomBffInput implements OperationInput {
    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @NotNull(message = "Start date is required")
    private LocalDate endDate;

    @BedSizeValidation(optional = true)
    private String bedSize;

    @BathroomTypeValidation(optional = true)
    private String bathroomType;
}