package com.api.rest.apiRest.controllers.dto;

import com.api.rest.apiRest.model.Customer;
import com.api.rest.apiRest.model.Employee;
import com.api.rest.apiRest.model.Payment;
import com.api.rest.apiRest.model.TouristPackage;
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

    private Long  fk_package_id;

    private Long fk_payment_id;

    private Long fk_employee_id;

    private Long fk_customer_id;

    private String customerName;

}
