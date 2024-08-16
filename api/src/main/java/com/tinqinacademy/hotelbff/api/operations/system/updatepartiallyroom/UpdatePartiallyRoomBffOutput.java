package com.tinqinacademy.hotelbff.api.operations.system.updatepartiallyroom;

import com.tinqinacademy.hotelbff.api.base.OperationOutput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UpdatePartiallyRoomBffOutput implements OperationOutput {
    private UUID id;
}

