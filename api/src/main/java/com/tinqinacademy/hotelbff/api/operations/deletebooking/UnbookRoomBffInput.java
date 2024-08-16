package com.tinqinacademy.hotelbff.api.operations.deletebooking;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.hotelbff.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class UnbookRoomBffInput implements OperationInput {
    @JsonIgnore
    private String bookingId;

    @JsonIgnore
    private String userContextId;
}
