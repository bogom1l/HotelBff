package com.tinqinacademy.hotelbff.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsOutput;
import com.tinqinacademy.hotelbff.api.operations.comment.getcomments.GetCommentsBffOutput;
import com.tinqinacademy.hotelbff.api.restroutes.RestApiRoutes;
import com.tinqinacademy.hotelbff.domain.feignclients.AuthRestClient;
import com.tinqinacademy.hotelbff.domain.feignclients.CommentsRestClient;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CommentControllerTest {
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

    @MockBean
    private CommentsRestClient commentsRestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllComments() throws Exception {
        String roomId = UUID.randomUUID().toString();
        GetCommentsBffOutput output = GetCommentsBffOutput.builder()
                .comments(new ArrayList<>())
                .build();

        when(commentsRestClient.getAllComments(roomId)).thenReturn(GetCommentsOutput.builder().build());

        mvc.perform(get(RestApiRoutes.GET_ALL_COMMENTS, roomId))
                .andExpect(status().isOk());
    }


}
