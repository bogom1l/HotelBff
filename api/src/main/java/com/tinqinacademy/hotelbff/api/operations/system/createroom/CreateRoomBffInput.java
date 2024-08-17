package com.tinqinacademy.hotelbff.api.operations.system.createroom;

import com.tinqinacademy.hotelbff.api.base.OperationInput;
import com.tinqinacademy.hotelbff.api.validation.bathroomtype.BathroomTypeValidation;
import com.tinqinacademy.hotelbff.api.validation.bedsize.BedSizeValidation;
import com.tinqinacademy.hotelbff.api.validation.room.RoomNumberValidation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateRoomBffInput implements OperationInput {
    @BedSizeValidation
    private String bedSize;

    @BathroomTypeValidation
    private String bathroomType;

    @NotNull(message = "Floor is mandatory")
    @Min(value = 0, message = "Floor should be at least 0")
    @Max(value = 15, message = "Floor should be maximum 15")
    private Integer floor;

    @RoomNumberValidation
    private String roomNumber;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price should be positive")
    private BigDecimal price;
}
