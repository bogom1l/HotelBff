package com.tinqinacademy.hotelbff.core.converters.bookroom;

import com.tinqinacademy.hotel.api.operations.hotel.bookroom.BookRoomInput;
import com.tinqinacademy.hotelbff.api.operations.bookroom.BookRoomBffInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookRoomBffInputToBookRoomInput implements Converter<BookRoomBffInput, BookRoomInput> {

    @Override
    public BookRoomInput convert(BookRoomBffInput source) {
        log.info("Started Converter - BookRoomBffInput to BookRoomInput");

        BookRoomInput output = BookRoomInput.builder()
                .startDate(source.getStartDate())
                .endDate(source.getEndDate())
                .userId(source.getUserContextId())
                .build();

        log.info("Ended Converter - BookRoomBffInput to BookRoomInput");
        return output;
    }
}
