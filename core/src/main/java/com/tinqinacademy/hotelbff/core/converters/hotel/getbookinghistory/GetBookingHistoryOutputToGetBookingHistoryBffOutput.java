package com.tinqinacademy.hotelbff.core.converters.hotel.getbookinghistory;

import com.tinqinacademy.hotel.api.operations.hotel.getbookinghistory.GetBookingHistoryOutput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.BookingBffOutput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.GetBookingHistoryBffOutput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.GuestBffOutput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.RoomBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class GetBookingHistoryOutputToGetBookingHistoryBffOutput implements Converter<GetBookingHistoryOutput, GetBookingHistoryBffOutput> {
    private final ConversionService conversionService;

    @Lazy
    public GetBookingHistoryOutputToGetBookingHistoryBffOutput(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public GetBookingHistoryBffOutput convert(GetBookingHistoryOutput source) {
        log.info("Started Converter - GetBookingHistoryOutput to GetBookingHistoryBffOutput");

        List<BookingBffOutput> bookingsBffOutput = source.getBookings().stream()
                .map(booking -> BookingBffOutput.builder()
                        .bookingStartDate(booking.getBookingStartDate())
                        .bookingEndDate(booking.getBookingEndDate())
                        .bookingTotalPrice(booking.getBookingTotalPrice())
                        .room(conversionService.convert(booking.getRoom(), RoomBffOutput.class))
                        .guests(booking.getGuests().stream()
                                .map(guest -> conversionService.convert(guest, GuestBffOutput.class))
                                .toList())
                        .build())
                .toList();

        GetBookingHistoryBffOutput target = GetBookingHistoryBffOutput.builder()
                .bookings(bookingsBffOutput)
                .build();

        log.info("Ended Converter - GetBookingHistoryOutput to GetBookingHistoryBffOutput");
        return target;
    }
}
