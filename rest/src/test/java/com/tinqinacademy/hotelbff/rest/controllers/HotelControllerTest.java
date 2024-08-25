package com.tinqinacademy.hotelbff.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.authentication.api.operations.validatejwt.ValidateJwtOutput;
import com.tinqinacademy.hotel.api.operations.hotel.bookroom.BookRoomInput;
import com.tinqinacademy.hotel.api.operations.hotel.bookroom.BookRoomOutput;
import com.tinqinacademy.hotel.api.operations.hotel.checkavailableroom.CheckAvailableRoomOutput;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.domain.feignclients.AuthRestClient;
import com.tinqinacademy.hotelbff.domain.feignclients.HotelRestClient;
import com.tinqinacademy.hotelbff.rest.security.JwtDecoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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
    void bookRoomReturnsUnauthorized() throws Exception {
        String jwtHeader = "Bearer 123123123";
        String roomId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();

        BookRoomInput bookRoomInput = BookRoomInput.builder()
                .startDate(LocalDate.of(2026, 11, 11))
                .endDate(LocalDate.of(2026, 11, 14))
                .userId(userId)
                .roomId(roomId)
                .build();

        when(authRestClient.validateJwt(jwtHeader))
                .thenReturn(
                        ValidateJwtOutput.builder()
                                .isValid(true)
                                .build());

        Map<String, String> claims = new HashMap<>();
        claims.put("sub", userId);
        claims.put("role", "USER");
        when(jwtDecoder.getPayloadFromJwt(jwtHeader))
                .thenReturn(claims);

        when(hotelRestClient.bookRoom(roomId, bookRoomInput))
                .thenReturn(BookRoomOutput.builder().build());

        mvc.perform(post(RestApiRoutes.BOOK_ROOM, roomId)
                .header(HttpHeaders.AUTHORIZATION, jwtHeader)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(bookRoomInput))
        ).andExpect(status().isUnauthorized());
    }

}
