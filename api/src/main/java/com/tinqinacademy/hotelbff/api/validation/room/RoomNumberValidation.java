package com.tinqinacademy.hotelbff.api.validation.room;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // will be applied to fields
@Retention(RetentionPolicy.RUNTIME) // will be available on runtime
@Constraint(validatedBy = RoomNumberValidator.class) // implementing custom validation logic from the RoomNumberValidator class
public @interface RoomNumberValidation {
    String message() default "Invalid room number: Valid Format - A100, A101, B205, H804, P999";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean optional() default false;
}