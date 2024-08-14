package com.tinqinacademy.hotelbff.api.operations.checkavailableroom;

import com.tinqinacademy.hotelbff.api.base.OperationOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CheckAvailableRoomBffOutput implements OperationOutput {
    private List<String> ids;
}
