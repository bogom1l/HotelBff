package com.tinqinacademy.hotelbff.api.operations.registerguest;

import com.tinqinacademy.hotelbff.api.base.OperationInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterGuestBffInput implements OperationInput {
    @NotEmpty(message = "Guests list should not be empty")
    private List<@Valid GuestBffInput> guests;

    @NotBlank(message = "Room id is mandatory")
    private String roomId;
}
