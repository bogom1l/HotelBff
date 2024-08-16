package com.tinqinacademy.hotelbff.api.operations.hotel.getroombasicinfo;

import com.tinqinacademy.hotelbff.api.base.OperationOutput;
import com.tinqinacademy.hotelbff.api.enums.BathroomType;
import com.tinqinacademy.hotelbff.api.enums.BedSize;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetRoomBasicInfoBffOutput implements OperationOutput {
    private UUID id;

    private BigDecimal price;

    private Integer floor;

    private BedSize bedSize;

    private BathroomType bathroomType;

    private List<LocalDate> datesOccupied;

    private String roomNumber;
}
