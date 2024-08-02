package com.tinqinacademy.hotelbff.api.operations;

import com.tinqinacademy.hotelbff.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TestInput implements OperationInput {
    private String firstName; // sample class
}
