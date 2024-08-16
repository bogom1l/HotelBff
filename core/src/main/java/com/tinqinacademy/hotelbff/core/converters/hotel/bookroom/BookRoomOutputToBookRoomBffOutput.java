package com.tinqinacademy.hotelbff.core.converters.hotel.bookroom;

import com.tinqinacademy.hotel.api.operations.hotel.bookroom.BookRoomOutput;
import com.tinqinacademy.hotelbff.api.operations.bookroom.BookRoomBffOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookRoomOutputToBookRoomBffOutput implements Converter<BookRoomOutput, BookRoomBffOutput> {
    @Override
    public BookRoomBffOutput convert(BookRoomOutput source) {
        log.info("Started Converter - BookRoomOutput to BookRoomBffOutput");
        BookRoomBffOutput output = BookRoomBffOutput.builder().build();
        log.info("Ended Converter - BookRoomOutput to BookRoomBffOutput");
        return output;
    }
}
