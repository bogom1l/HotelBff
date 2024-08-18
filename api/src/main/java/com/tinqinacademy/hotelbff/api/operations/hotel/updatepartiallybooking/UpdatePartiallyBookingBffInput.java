package com.tinqinacademy.hotelbff.api.operations.hotel.updatepartiallybooking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.hotelbff.api.base.OperationInput;
import com.tinqinacademy.hotelbff.api.validation.room.RoomNumberValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class UpdatePartiallyBookingBffInput implements OperationInput {
    @JsonIgnore
    private String bookingId;

    private String startDate;

    private String endDate;

    @Positive
    private BigDecimal totalPrice;

    @RoomNumberValidation(optional = true)
    private String roomNumber;

    private List<@Valid UpdatePartiallyGuestBffInput> guests;
}
