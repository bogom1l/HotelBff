package com.tinqinacademy.hotelbff.rest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqinacademy.comments.api.operations.getcomments.CommentOutput;
import com.tinqinacademy.comments.api.operations.getcomments.GetCommentsOutput;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    void getAllCommentsReturnsOk() throws Exception {
        String roomId = UUID.randomUUID().toString();

        List<CommentOutput> comments = new ArrayList<>();
        comments.add(CommentOutput.builder()
                .id(UUID.randomUUID().toString())
                .content("This is a comment")
                .userId(UUID.randomUUID().toString())
                .lastEditedBy(UUID.randomUUID().toString())
                .lastEditedDate(LocalDate.parse("2021-08-01"))
                .publishDate(LocalDate.parse("2021-08-01"))
                .build());

        when(commentsRestClient.getAllComments(roomId))
                .thenReturn(GetCommentsOutput.builder()
                        .comments(comments)
                        .build());

        mvc.perform(get(RestApiRoutes.GET_ALL_COMMENTS, roomId))
                .andExpect(status().isOk());
    }

    @Test
    void addCommentReturnsOk() throws Exception {
        String roomId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();

        mvc.perform(get(RestApiRoutes.ADD_COMMENT, roomId)
                .content(mapper.writeValueAsString(CommentOutput.builder()
                        .content("This is a comment")
                        .build())))
                .andExpect(status().isOk());
    }


}
