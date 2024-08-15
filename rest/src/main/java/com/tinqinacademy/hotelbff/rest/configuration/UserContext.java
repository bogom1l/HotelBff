package com.tinqinacademy.hotelbff.rest.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Getter
@Setter
@Component
@RequestScope //todo ? By using @RequestScope, the userId gets cleared after each HTTP request
public class UserContext {
    private String userId;
}
