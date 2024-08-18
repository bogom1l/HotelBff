package com.tinqinacademy.hotelbff.rest.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope // Defining the scope of the Bean - a new instance is created for each HTTP Request
@Setter
@Getter
public class UserContext {
    private String userId;
}
