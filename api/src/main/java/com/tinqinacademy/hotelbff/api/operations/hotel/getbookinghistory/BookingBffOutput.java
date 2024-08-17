package com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookingBffOutput {
    private RoomBffOutput room;

    private LocalDate bookingStartDate;

    private LocalDate bookingEndDate;

    private BigDecimal bookingTotalPrice;

    private List<GuestBffOutput> guests;
}
