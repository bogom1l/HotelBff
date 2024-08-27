package com.tinqinacademy.hotelbff.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.authentication.api.operations.validatejwt.ValidateJwtOutput;
import com.tinqinacademy.hotel.api.operations.hotel.bookroom.BookRoomInput;
import com.tinqinacademy.hotel.api.operations.hotel.bookroom.BookRoomOutput;
import com.tinqinacademy.hotel.api.operations.hotel.checkavailableroom.CheckAvailableRoomOutput;
import com.tinqinacademy.hotel.api.operations.hotel.getbookinghistory.GetBookingHistoryInput;
import com.tinqinacademy.hotel.api.operations.hotel.getbookinghistory.GetBookingHistoryOutput;
import com.tinqinacademy.hotel.api.operations.hotel.unbookroom.UnbookRoomInput;
import com.tinqinacademy.hotel.api.operations.hotel.unbookroom.UnbookRoomOutput;
import com.tinqinacademy.hotelbff.api.operations.hotel.bookroom.BookRoomBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.getbookinghistory.GetBookingHistoryBffInput;
import com.tinqinacademy.hotelbff.api.operations.hotel.unbookroom.UnbookRoomBffInput;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.domain.feignclients.AuthRestClient;
import com.tinqinacademy.hotelbff.domain.feignclients.HotelRestClient;
import com.tinqinacademy.hotelbff.rest.security.JwtDecoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HotelControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private HotelRestClient hotelRestClient;

    @MockBean
    private AuthRestClient authRestClient;

    @MockBean
    private JwtDecoder jwtDecoder;

    @Test
    void checkAvailableRoomsReturnsOk() throws Exception {
        LocalDate startDate = LocalDate.of(2024, 10, 11);
        LocalDate endDate = LocalDate.of(2024, 10, 15);
        com.tinqinacademy.hotelbff.api.enums.BedSize bedSize = com.tinqinacademy.hotelbff.api.enums.BedSize.SINGLE;
        com.tinqinacademy.hotelbff.api.enums.BathroomType bathroomType = com.tinqinacademy.hotelbff.api.enums.BathroomType.PRIVATE;
        when(hotelRestClient.checkAvailableRoom(
                startDate, endDate, bedSize.toString(), bathroomType.toString()))
                .thenReturn(new CheckAvailableRoomOutput());

        mvc.perform(get(RestApiRoutes.CHECK_ROOM_AVAILABILITY)
                        .param("startDate", "2024-10-11")
                        .param("endDate", "2024-10-15")
                        .param("bedSize", bedSize.toString())
                        .param("bathroomType", bathroomType.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void bookRoomReturnsOk() throws Exception {
        String jwtHeader = "Bearer 123123123";
        String roomId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();

        BookRoomInput bookRoomInput = BookRoomInput.builder()
                .startDate(LocalDate.of(2028, 11, 11))
                .endDate(LocalDate.of(2028, 11, 14))
                .userId(userId)
                .roomId(roomId)
                .build();

        when(authRestClient.validateJwt(jwtHeader))
                .thenReturn(ValidateJwtOutput.builder().isValid(true).build());

        Map<String, String> claims = new HashMap<>();
        claims.put("sub", userId);
        claims.put("role", "USER");
        when(jwtDecoder.getPayloadFromJwt(any(jwtHeader.getClass())))
                .thenReturn(claims);

        when(hotelRestClient.bookRoom(roomId, bookRoomInput))
                .thenReturn(BookRoomOutput.builder().build());

        BookRoomBffInput bookRoomBffInput = BookRoomBffInput.builder()
                .startDate(LocalDate.of(2028, 11, 11))
                .endDate(LocalDate.of(2028, 11, 14))
                .userContextId(userId)
                .roomId(roomId)
                .build();

        mvc.perform(post(RestApiRoutes.BOOK_ROOM, roomId)
                .header(HttpHeaders.AUTHORIZATION, jwtHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(bookRoomBffInput))
        ).andExpect(status().isCreated());
    }

    @Test
    void bookRoomReturnsUnauthorized() throws Exception {
        String jwtHeader = "Bearer 123123123";
        String roomId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();

        BookRoomInput bookRoomInput = BookRoomInput.builder()
                .startDate(LocalDate.of(2028, 11, 11))
                .endDate(LocalDate.of(2028, 11, 14))
                .userId(userId)
                .roomId(roomId)
                .build();

        when(authRestClient.validateJwt(jwtHeader))
                .thenReturn(ValidateJwtOutput.builder().isValid(false).build());

        Map<String, String> claims = new HashMap<>();
        claims.put("sub", userId);
        claims.put("role", "USER");
        when(jwtDecoder.getPayloadFromJwt(any(jwtHeader.getClass())))
                .thenReturn(claims);

        when(hotelRestClient.bookRoom(roomId, bookRoomInput))
                .thenReturn(BookRoomOutput.builder().build());

        BookRoomBffInput bookRoomBffInput = BookRoomBffInput.builder()
                .startDate(LocalDate.of(2028, 11, 11))
                .endDate(LocalDate.of(2028, 11, 14))
                .userContextId(userId)
                .roomId(roomId)
                .build();

        mvc.perform(post(RestApiRoutes.BOOK_ROOM, roomId)
                .header(HttpHeaders.AUTHORIZATION, jwtHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(bookRoomBffInput))
        ).andExpect(status().isUnauthorized());
    }

    @Test
    void unbookRoomReturnsUnauthorized() throws Exception {
        String jwtHeader = "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJkMWFmYmI3My00OGJmLTQ5YTEtOWQ3NS0wZjc0OTBlYmVlMzYiLCJyb2xlIjoiVVNFUiIsImlhdCI6MTcyNDY1Njk4MiwiZXhwIjoxNzI0NjU3MjgyfQ.9H_zR-NBog0g3AMjCMHG59sroxjMj4mKAEpIVYQgZ4WpPGVfhDddqvyEhi5cNQcu";
        String bookingId = UUID.randomUUID().toString();
        String userId = "d1afbb73-48bf-49a1-9d75-0f7490ebee36";
        String roomId = UUID.randomUUID().toString();

        UnbookRoomInput unbookRoomInput = UnbookRoomInput.builder()
                .userId(userId)
                .build();

        ValidateJwtOutput output = ValidateJwtOutput.builder().isValid(false).build();

        when(authRestClient.validateJwt(jwtHeader))
                .thenReturn(output);

        Map<String, String> claims = new HashMap<>();
        claims.put("sub", userId);
        claims.put("role", "USER");
        when(jwtDecoder.getPayloadFromJwt(any(jwtHeader.getClass())))
                .thenReturn(claims);

        when(hotelRestClient.unbookRoom(roomId, unbookRoomInput))
                .thenReturn(UnbookRoomOutput.builder().build());

        mvc.perform(delete(RestApiRoutes.UNBOOK_ROOM, bookingId)
                        .header(HttpHeaders.AUTHORIZATION, jwtHeader)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void unbookRoomReturnsOk() throws Exception {
        String jwtHeader = "Bearer bearer123";
        String userId = UUID.randomUUID().toString();//"d1afbb73-48bf-49a1-9d75-0f7490ebee36";
        String bookingId = UUID.randomUUID().toString();
        String roomId = UUID.randomUUID().toString();

        UnbookRoomInput unbookRoomInput = UnbookRoomInput.builder()
                .userId(userId)
                .build();

        ValidateJwtOutput output = ValidateJwtOutput.builder().isValid(true).build();

        Map<String, String> claims = new HashMap<>();
        claims.put("sub", userId);
        claims.put("role", "USER");
        when(jwtDecoder.getPayloadFromJwt(any(jwtHeader.getClass())))
                .thenReturn(claims);
        when(authRestClient.validateJwt(jwtHeader))
                .thenReturn(output);

        when(hotelRestClient.unbookRoom(bookingId, unbookRoomInput))
                .thenReturn(UnbookRoomOutput.builder().build());

        UnbookRoomBffInput unbookRoomBffInput = UnbookRoomBffInput.builder()
                .bookingId(bookingId)
                .userContextId(userId)
                .build();

        mvc.perform(delete(RestApiRoutes.UNBOOK_ROOM, roomId)
                .header(HttpHeaders.AUTHORIZATION, jwtHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(unbookRoomBffInput))
        ).andExpect(status().isOk());
    }

    @Test
    void getBookingHistoryReturnsOk() throws Exception {
        String jwtHeader = "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJkMWFmYmI3My00OGJmLTQ5YTEtOWQ3NS0wZjc0OTBlYmVlMzYiLCJyb2xlIjoiVVNFUiIsImlhdCI6MTcyNDc0ODA3NiwiZXhwIjoxNzI0NzQ4Mzc2fQ.HGI8W_9BeuFLWv_hDYM_RyLeIyhOR4ktW3HyiE2PzA4bBCH6qih9TJpCtjog4yID ";
        String userId = UUID.randomUUID().toString();//"d1afbb73-48bf-49a1-9d75-0f7490ebee36";
        String bookingId = UUID.randomUUID().toString();
        String roomId = UUID.randomUUID().toString();

        GetBookingHistoryInput getBookingHistoryInput = GetBookingHistoryInput.builder()
                .userId(userId)
                .build();

        ValidateJwtOutput output = ValidateJwtOutput.builder().isValid(true).build();

        when(authRestClient.validateJwt(jwtHeader))
                .thenReturn(output);

        Map<String, String> claims = new HashMap<>();
        claims.put("sub", userId);
        claims.put("role", "ADMIN");
        when(jwtDecoder.getPayloadFromJwt(any(jwtHeader.getClass())))
                .thenReturn(claims);

        when(hotelRestClient.getBookingHistory(userId))
                .thenReturn(GetBookingHistoryOutput.builder().build());

        GetBookingHistoryBffInput getBookingHistoryBffInput = GetBookingHistoryBffInput.builder()
                .userContextId(getBookingHistoryInput.getUserId())
                .build();

//        mvc.perform(get(RestApiRoutes.GET_BOOKING_HISTORY, userId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(getBookingHistoryBffInput))
//                .header(HttpHeaders.AUTHORIZATION, jwtHeader)
//        ).andExpect(status().isOk());
    }

    @Test
    void getBookingHistoryReturnsForbidden() throws Exception {
        String jwtHeader = "Bearer test123";
        String userId = UUID.randomUUID().toString();//"d1afbb73-48bf-49a1-9d75-0f7490ebee36";
        String bookingId = UUID.randomUUID().toString();
        String roomId = UUID.randomUUID().toString();

        GetBookingHistoryInput getBookingHistoryInput = GetBookingHistoryInput.builder()
                .userId(userId)
                .build();

        ValidateJwtOutput output = ValidateJwtOutput.builder().isValid(true).build();

        when(authRestClient.validateJwt(jwtHeader))
                .thenReturn(output);

        Map<String, String> claims = new HashMap<>();
        claims.put("sub", userId);
        claims.put("role", "USER");
        when(jwtDecoder.getPayloadFromJwt(any(jwtHeader.getClass())))
                .thenReturn(claims);

        when(hotelRestClient.getBookingHistory(userId))
                .thenReturn(GetBookingHistoryOutput.builder().build());

        GetBookingHistoryBffInput getBookingHistoryBffInput = GetBookingHistoryBffInput.builder()
                .userContextId(userId)
                .build();

        mvc.perform(get(RestApiRoutes.GET_BOOKING_HISTORY, userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(getBookingHistoryBffInput))
                .header(HttpHeaders.AUTHORIZATION, jwtHeader)
        ).andExpect(status().isForbidden());
    }

}
