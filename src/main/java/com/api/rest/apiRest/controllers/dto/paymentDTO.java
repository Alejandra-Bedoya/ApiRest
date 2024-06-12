package com.api.rest.apiRest.controllers.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class paymentDTO {

    private Long paymentId;

    private Double paymentAmount;

    private LocalDateTime paymentDate;

    private String paymentMethod;

}
