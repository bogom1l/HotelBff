package com.tinqinacademy.hotelbff.api.operations.system.updatepartiallyroom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tinqinacademy.hotelbff.api.base.OperationInput;
import com.tinqinacademy.hotelbff.api.validation.bathroomtype.BathroomTypeValidation;
import com.tinqinacademy.hotelbff.api.validation.bedsize.BedSizeValidation;
import com.tinqinacademy.hotelbff.api.validation.room.RoomNumberValidation;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class UpdatePartiallyRoomBffInput implements OperationInput {
    @JsonIgnore
    private String roomId;

    @BedSizeValidation(optional = true)
    private String bedSize;

    @BathroomTypeValidation(optional = true)
    private String bathroomType;

    @RoomNumberValidation(optional = true)
    private String roomNumber;

    @Positive(message = "Price should be positive")
    private BigDecimal price;
}

