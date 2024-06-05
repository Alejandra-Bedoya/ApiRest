package com.api.rest.apiRest.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    @Column(name = "departure_date", nullable = false)
    private LocalDate departureDate;

    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Column(name = "duration_days", nullable = false)
    private Integer durationDays;

    @Column(name = "package_price", nullable = false)
    private Double packagePrice;

    @Column(name = "available_spots", nullable = false)
    private Integer availableSpots;

}
