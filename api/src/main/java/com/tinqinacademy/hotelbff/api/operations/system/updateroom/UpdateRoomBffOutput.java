package com.tinqinacademy.hotelbff.api.operations.system.updateroom;

import com.tinqinacademy.hotelbff.api.base.OperationOutput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class UpdateRoomBffOutput implements OperationOutput {
    private UUID id;
}
