package com.tinqinacademy.hotelbff.rest.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // Enable serialization/deserialization of Java 8 date/time types
        mapper.registerModule(new JavaTimeModule());

        // Prevent serialization failures for empty beans
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // Serialize dates in a human-readable format
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Exclude null properties from JSON output
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper;
    }
}