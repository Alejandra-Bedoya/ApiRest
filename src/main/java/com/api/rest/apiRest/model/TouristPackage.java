package com.api.rest.apiRest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TouristPackages")
public class TouristPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id", nullable = false)
    private Long packageId;

    @Column(name = "package_category", nullable = false)
    private String packageCategory;

    @Column(name = "destination_place", nullable = false)
    private String destinationPlace;

    @Column(name = "package_description", nullable = false)
    private String packageDescription;

    @Column(name = "departure_date", nullable = false) // Fecha de salida del viaje
    private LocalDate departureDate;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;  // Fecha de regreso del viaje

    @Column(name = "duration_days", nullable = false)
    private Integer durationDays;

    @Column(name = "package_price", nullable = false)
    private Double packagePrice;

    @Column(name = "available_spots", nullable = false) //lugares disponibles
    private Integer availableSpots;

}
