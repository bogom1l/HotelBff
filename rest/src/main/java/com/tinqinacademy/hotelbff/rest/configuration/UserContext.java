package com.tinqinacademy.hotelbff.rest.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;


@Component
@RequestScope // Defining the scope of the bean - a new instance is created for each HTTP request
@Setter
@Getter
public class UserContext {
    private String userId;
}
