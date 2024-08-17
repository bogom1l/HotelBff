package com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory;

import com.tinqinacademy.hotelbff.api.base.OperationOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetBookingHistoryBffOutput implements OperationOutput {
    private List<BookingBffOutput> bookings;
}
