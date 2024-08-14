package com.tinqinacademy.hotelbff.api.operations.getroombasicinfo;

import com.tinqinacademy.hotelbff.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetRoomBasicInfoBffInput implements OperationInput {
    private String roomId;
}
