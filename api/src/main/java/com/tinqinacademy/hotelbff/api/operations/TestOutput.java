package com.tinqinacademy.hotelbff.api.operations;


import com.tinqinacademy.hotelbff.api.base.OperationOutput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TestOutput implements OperationOutput {
    private String firstName; // sample class
}