package com.tinqinacademy.hotelbff.rest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

/**
 * This class decodes the payload from a JWT token received in the Authorization header.
 */
@Component
@RequiredArgsConstructor
public class JwtDecoder {
    private final ObjectMapper objectMapper;

    public Map<String, Object> getPayloadFromJwt(String authorizationHeader) throws IOException {
        String token = authorizationHeader.substring(7);

        String[] chunks = token.split("\\.");

        // Decode the payload part from Base64 URL encoding
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        // Converts the JSON payload string into a Map<String, Object>
        return objectMapper.readValue(payload, Map.class);
    }
}