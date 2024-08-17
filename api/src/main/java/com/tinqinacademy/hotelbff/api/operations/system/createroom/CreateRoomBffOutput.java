package com.tinqinacademy.hotelbff.api.operations.system.createroom;

import com.tinqinacademy.hotelbff.api.base.OperationOutput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CreateRoomBffOutput implements OperationOutput {
    private UUID id;
}