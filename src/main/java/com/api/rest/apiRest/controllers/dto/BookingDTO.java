package com.api.rest.apiRest.controllers.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {

    private Long bookingId;

    private LocalDateTime bookingDate;

    private Long  fkPackageId;

    private Long fkPaymentId;

    private Long fkEmployeeId;

    private Long fkCustomerId;

    private  String customerName;

    private String destinationPlace;

}
