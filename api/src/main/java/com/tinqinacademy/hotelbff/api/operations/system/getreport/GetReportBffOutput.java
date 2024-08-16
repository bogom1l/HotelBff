package com.tinqinacademy.hotelbff.api.operations.system.getreport;

import com.tinqinacademy.hotelbff.api.base.OperationOutput;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class GetReportBffOutput implements OperationOutput {
    private List<GuestBffOutput> guests;
}
