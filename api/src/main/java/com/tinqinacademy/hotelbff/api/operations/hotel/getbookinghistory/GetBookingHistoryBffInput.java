package com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.hotelbff.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class GetBookingHistoryBffInput implements OperationInput {
    @JsonIgnore
    private String userContextId;
}
