package com.tinqinacademy.hotelbff.rest.security;

import com.fasterxml.jackson.core.type.TypeReference;
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

    public Map<String, String> getPayloadFromJwt(String authorizationHeader) throws IOException {
        // Remove "Bearer " prefix
        String token = authorizationHeader.substring(7);

        // Split token into header, payload, and signature
        String[] chunks = token.split("\\.");

        // Decode the payload part from Base64 URL encoding
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        // Payload:
        // "sub" - "{userId}" (whom the token refers to)
        // "role" - "{USER/ADMIN}"
        // "iat" - "{issuedAtDate}"
        // "exp" - "{expirationDate}"

        // Converts the JSON payload string into a Map<String, String>
        //  , because the payload is a JSON object
        //  , and we want to access the user ID and role from it
        return objectMapper.readValue(payload, new TypeReference<Map<String, String>>() {});
    }
}