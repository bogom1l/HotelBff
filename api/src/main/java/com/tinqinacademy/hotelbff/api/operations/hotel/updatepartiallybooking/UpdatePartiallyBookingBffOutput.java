package com.tinqinacademy.hotelbff.api.operations.hotel.updatepartiallybooking;

import com.tinqinacademy.hotelbff.api.base.OperationOutput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UpdatePartiallyBookingBffOutput implements OperationOutput {
    private UUID id;
}
