package com.tinqinacademy.hotelbff.api.operations.system.updateroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.hotelbff.api.base.OperationInput;
import com.tinqinacademy.hotelbff.api.validation.bathroomtype.BathroomTypeValidation;
import com.tinqinacademy.hotelbff.api.validation.bedsize.BedSizeValidation;
import com.tinqinacademy.hotelbff.api.validation.room.RoomNumberValidation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class UpdateRoomBffInput implements OperationInput {
    @JsonIgnore
    private String roomId;

    @BedSizeValidation
    private String bedSize;

    @BathroomTypeValidation
    private String bathroomType;

    @RoomNumberValidation
    private String roomNumber;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price should be positive")
    private BigDecimal price;
}
