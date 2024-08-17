package com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoomBffOutput {
    private String roomNumber;

    private BigDecimal roomPrice;

    private Integer roomFloor;
}
