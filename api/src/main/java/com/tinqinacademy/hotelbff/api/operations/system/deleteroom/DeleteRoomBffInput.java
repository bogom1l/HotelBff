package com.tinqinacademy.hotelbff.api.operations.system.deleteroom;

import com.tinqinacademy.hotelbff.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DeleteRoomBffInput implements OperationInput {
    private String id;
}
