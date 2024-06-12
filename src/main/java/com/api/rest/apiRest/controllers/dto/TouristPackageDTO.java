package com.api.rest.apiRest.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TouristPackageDTO {

    private Long packageId;

    private String packageCategory;

    private String destinationPlace;

    private String packageDescription;

    private LocalDate departureDate;

    private LocalDate returnDate;

    private Integer durationDays;

    private Double packagePrice;

    private Integer availableSpots;



}
